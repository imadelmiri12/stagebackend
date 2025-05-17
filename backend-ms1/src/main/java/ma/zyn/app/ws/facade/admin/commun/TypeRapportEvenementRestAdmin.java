package  ma.zyn.app.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.commun.TypeRapportEvenement;
import ma.zyn.app.dao.criteria.core.commun.TypeRapportEvenementCriteria;
import ma.zyn.app.service.facade.admin.commun.TypeRapportEvenementAdminService;
import ma.zyn.app.ws.converter.commun.TypeRapportEvenementConverter;
import ma.zyn.app.ws.dto.commun.TypeRapportEvenementDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/typeRapportEvenement/")
public class TypeRapportEvenementRestAdmin {




    @Operation(summary = "Finds a list of all typeRapportEvenements")
    @GetMapping("")
    public ResponseEntity<List<TypeRapportEvenementDto>> findAll() throws Exception {
        ResponseEntity<List<TypeRapportEvenementDto>> res = null;
        List<TypeRapportEvenement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeRapportEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeRapportEvenements")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeRapportEvenementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeRapportEvenementDto>> res = null;
        List<TypeRapportEvenement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeRapportEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeRapportEvenement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeRapportEvenementDto> findById(@PathVariable Long id) {
        TypeRapportEvenement t = service.findById(id);
        if (t != null) {
            TypeRapportEvenementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeRapportEvenement by label")
    @GetMapping("label/{label}")
    public ResponseEntity<TypeRapportEvenementDto> findByLabel(@PathVariable String label) {
	    TypeRapportEvenement t = service.findByReferenceEntity(new TypeRapportEvenement(label));
        if (t != null) {
            TypeRapportEvenementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeRapportEvenement")
    @PostMapping("")
    public ResponseEntity<TypeRapportEvenementDto> save(@RequestBody TypeRapportEvenementDto dto) throws Exception {
        if(dto!=null){
            TypeRapportEvenement myT = converter.toItem(dto);
            TypeRapportEvenement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeRapportEvenementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeRapportEvenement")
    @PutMapping("")
    public ResponseEntity<TypeRapportEvenementDto> update(@RequestBody TypeRapportEvenementDto dto) throws Exception {
        ResponseEntity<TypeRapportEvenementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeRapportEvenement t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeRapportEvenement updated = service.update(t);
            TypeRapportEvenementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeRapportEvenement")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeRapportEvenementDto>> delete(@RequestBody List<TypeRapportEvenementDto> dtos) throws Exception {
        ResponseEntity<List<TypeRapportEvenementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeRapportEvenement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeRapportEvenement")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a typeRapportEvenement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeRapportEvenementDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeRapportEvenement loaded =  service.findWithAssociatedLists(id);
        TypeRapportEvenementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeRapportEvenements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeRapportEvenementDto>> findByCriteria(@RequestBody TypeRapportEvenementCriteria criteria) throws Exception {
        ResponseEntity<List<TypeRapportEvenementDto>> res = null;
        List<TypeRapportEvenement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeRapportEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeRapportEvenements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeRapportEvenementCriteria criteria) throws Exception {
        List<TypeRapportEvenement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeRapportEvenementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeRapportEvenement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeRapportEvenementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeRapportEvenementDto> findDtos(List<TypeRapportEvenement> list){
        List<TypeRapportEvenementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeRapportEvenementDto> getDtoResponseEntity(TypeRapportEvenementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeRapportEvenementRestAdmin(TypeRapportEvenementAdminService service, TypeRapportEvenementConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TypeRapportEvenementAdminService service;
    private final TypeRapportEvenementConverter converter;





}
