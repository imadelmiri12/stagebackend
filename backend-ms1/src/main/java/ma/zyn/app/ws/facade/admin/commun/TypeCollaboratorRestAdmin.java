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

import ma.zyn.app.bean.core.commun.TypeCollaborator;
import ma.zyn.app.dao.criteria.core.commun.TypeCollaboratorCriteria;
import ma.zyn.app.service.facade.admin.commun.TypeCollaboratorAdminService;
import ma.zyn.app.ws.converter.commun.TypeCollaboratorConverter;
import ma.zyn.app.ws.dto.commun.TypeCollaboratorDto;
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
@RequestMapping("/api/admin/typeCollaborator/")
public class TypeCollaboratorRestAdmin {




    @Operation(summary = "Finds a list of all typeCollaborators")
    @GetMapping("")
    public ResponseEntity<List<TypeCollaboratorDto>> findAll() throws Exception {
        ResponseEntity<List<TypeCollaboratorDto>> res = null;
        List<TypeCollaborator> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeCollaboratorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeCollaborators")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeCollaboratorDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeCollaboratorDto>> res = null;
        List<TypeCollaborator> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeCollaboratorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeCollaborator by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeCollaboratorDto> findById(@PathVariable Long id) {
        TypeCollaborator t = service.findById(id);
        if (t != null) {
            TypeCollaboratorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeCollaborator by label")
    @GetMapping("label/{label}")
    public ResponseEntity<TypeCollaboratorDto> findByLabel(@PathVariable String label) {
	    TypeCollaborator t = service.findByReferenceEntity(new TypeCollaborator(label));
        if (t != null) {
            TypeCollaboratorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeCollaborator")
    @PostMapping("")
    public ResponseEntity<TypeCollaboratorDto> save(@RequestBody TypeCollaboratorDto dto) throws Exception {
        if(dto!=null){
            TypeCollaborator myT = converter.toItem(dto);
            TypeCollaborator t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeCollaboratorDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeCollaborator")
    @PutMapping("")
    public ResponseEntity<TypeCollaboratorDto> update(@RequestBody TypeCollaboratorDto dto) throws Exception {
        ResponseEntity<TypeCollaboratorDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeCollaborator t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeCollaborator updated = service.update(t);
            TypeCollaboratorDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeCollaborator")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeCollaboratorDto>> delete(@RequestBody List<TypeCollaboratorDto> dtos) throws Exception {
        ResponseEntity<List<TypeCollaboratorDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeCollaborator> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeCollaborator")
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


    @Operation(summary = "Finds a typeCollaborator and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeCollaboratorDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeCollaborator loaded =  service.findWithAssociatedLists(id);
        TypeCollaboratorDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeCollaborators by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeCollaboratorDto>> findByCriteria(@RequestBody TypeCollaboratorCriteria criteria) throws Exception {
        ResponseEntity<List<TypeCollaboratorDto>> res = null;
        List<TypeCollaborator> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeCollaboratorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeCollaborators by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeCollaboratorCriteria criteria) throws Exception {
        List<TypeCollaborator> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeCollaboratorDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeCollaborator data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeCollaboratorCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeCollaboratorDto> findDtos(List<TypeCollaborator> list){
        List<TypeCollaboratorDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeCollaboratorDto> getDtoResponseEntity(TypeCollaboratorDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeCollaboratorRestAdmin(TypeCollaboratorAdminService service, TypeCollaboratorConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TypeCollaboratorAdminService service;
    private final TypeCollaboratorConverter converter;





}
