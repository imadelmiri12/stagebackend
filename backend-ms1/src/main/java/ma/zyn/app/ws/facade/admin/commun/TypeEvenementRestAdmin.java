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

import ma.zyn.app.bean.core.commun.TypeEvenement;
import ma.zyn.app.dao.criteria.core.commun.TypeEvenementCriteria;
import ma.zyn.app.service.facade.admin.commun.TypeEvenementAdminService;
import ma.zyn.app.ws.converter.commun.TypeEvenementConverter;
import ma.zyn.app.ws.dto.commun.TypeEvenementDto;
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
@RequestMapping("/api/admin/typeEvenement/")
public class TypeEvenementRestAdmin {




    @Operation(summary = "Finds a list of all typeEvenements")
    @GetMapping("")
    public ResponseEntity<List<TypeEvenementDto>> findAll() throws Exception {
        ResponseEntity<List<TypeEvenementDto>> res = null;
        List<TypeEvenement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeEvenements")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeEvenementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeEvenementDto>> res = null;
        List<TypeEvenement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeEvenement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeEvenementDto> findById(@PathVariable Long id) {
        TypeEvenement t = service.findById(id);
        if (t != null) {
            TypeEvenementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeEvenement by label")
    @GetMapping("label/{label}")
    public ResponseEntity<TypeEvenementDto> findByLabel(@PathVariable String label) {
	    TypeEvenement t = service.findByReferenceEntity(new TypeEvenement(label));
        if (t != null) {
            TypeEvenementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeEvenement")
    @PostMapping("")
    public ResponseEntity<TypeEvenementDto> save(@RequestBody TypeEvenementDto dto) throws Exception {
        if(dto!=null){
            TypeEvenement myT = converter.toItem(dto);
            TypeEvenement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeEvenementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeEvenement")
    @PutMapping("")
    public ResponseEntity<TypeEvenementDto> update(@RequestBody TypeEvenementDto dto) throws Exception {
        ResponseEntity<TypeEvenementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeEvenement t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeEvenement updated = service.update(t);
            TypeEvenementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeEvenement")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeEvenementDto>> delete(@RequestBody List<TypeEvenementDto> dtos) throws Exception {
        ResponseEntity<List<TypeEvenementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeEvenement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeEvenement")
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


    @Operation(summary = "Finds a typeEvenement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeEvenementDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeEvenement loaded =  service.findWithAssociatedLists(id);
        TypeEvenementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeEvenements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeEvenementDto>> findByCriteria(@RequestBody TypeEvenementCriteria criteria) throws Exception {
        ResponseEntity<List<TypeEvenementDto>> res = null;
        List<TypeEvenement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeEvenementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeEvenements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeEvenementCriteria criteria) throws Exception {
        List<TypeEvenement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeEvenementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeEvenement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeEvenementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeEvenementDto> findDtos(List<TypeEvenement> list){
        List<TypeEvenementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeEvenementDto> getDtoResponseEntity(TypeEvenementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeEvenementRestAdmin(TypeEvenementAdminService service, TypeEvenementConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TypeEvenementAdminService service;
    private final TypeEvenementConverter converter;





}
