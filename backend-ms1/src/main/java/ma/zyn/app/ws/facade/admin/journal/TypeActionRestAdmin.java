package  ma.zyn.app.ws.facade.admin.journal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.journal.TypeAction;
import ma.zyn.app.dao.criteria.core.journal.TypeActionCriteria;
import ma.zyn.app.service.facade.admin.journal.TypeActionAdminService;
import ma.zyn.app.ws.converter.journal.TypeActionConverter;
import ma.zyn.app.ws.dto.journal.TypeActionDto;
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
@RequestMapping("/api/admin/typeAction/")
public class TypeActionRestAdmin {




    @Operation(summary = "Finds a list of all typeActions")
    @GetMapping("")
    public ResponseEntity<List<TypeActionDto>> findAll() throws Exception {
        ResponseEntity<List<TypeActionDto>> res = null;
        List<TypeAction> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeActionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeActions")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeActionDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeActionDto>> res = null;
        List<TypeAction> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeActionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeAction by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeActionDto> findById(@PathVariable Long id) {
        TypeAction t = service.findById(id);
        if (t != null) {
            TypeActionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeAction by label")
    @GetMapping("label/{label}")
    public ResponseEntity<TypeActionDto> findByLabel(@PathVariable String label) {
	    TypeAction t = service.findByReferenceEntity(new TypeAction(label));
        if (t != null) {
            TypeActionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeAction")
    @PostMapping("")
    public ResponseEntity<TypeActionDto> save(@RequestBody TypeActionDto dto) throws Exception {
        if(dto!=null){
            TypeAction myT = converter.toItem(dto);
            TypeAction t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeActionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeAction")
    @PutMapping("")
    public ResponseEntity<TypeActionDto> update(@RequestBody TypeActionDto dto) throws Exception {
        ResponseEntity<TypeActionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeAction t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeAction updated = service.update(t);
            TypeActionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeAction")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeActionDto>> delete(@RequestBody List<TypeActionDto> dtos) throws Exception {
        ResponseEntity<List<TypeActionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeAction> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeAction")
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


    @Operation(summary = "Finds a typeAction and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeActionDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeAction loaded =  service.findWithAssociatedLists(id);
        TypeActionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeActions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeActionDto>> findByCriteria(@RequestBody TypeActionCriteria criteria) throws Exception {
        ResponseEntity<List<TypeActionDto>> res = null;
        List<TypeAction> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeActionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeActions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeActionCriteria criteria) throws Exception {
        List<TypeAction> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeActionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeAction data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeActionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeActionDto> findDtos(List<TypeAction> list){
        List<TypeActionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeActionDto> getDtoResponseEntity(TypeActionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeActionRestAdmin(TypeActionAdminService service, TypeActionConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TypeActionAdminService service;
    private final TypeActionConverter converter;





}
