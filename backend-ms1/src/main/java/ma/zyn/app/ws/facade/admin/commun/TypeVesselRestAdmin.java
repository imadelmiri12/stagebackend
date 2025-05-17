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

import ma.zyn.app.bean.core.commun.TypeVessel;
import ma.zyn.app.dao.criteria.core.commun.TypeVesselCriteria;
import ma.zyn.app.service.facade.admin.commun.TypeVesselAdminService;
import ma.zyn.app.ws.converter.commun.TypeVesselConverter;
import ma.zyn.app.ws.dto.commun.TypeVesselDto;
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
@RequestMapping("/api/admin/typeVessel/")
public class TypeVesselRestAdmin {




    @Operation(summary = "Finds a list of all typeVessels")
    @GetMapping("")
    public ResponseEntity<List<TypeVesselDto>> findAll() throws Exception {
        ResponseEntity<List<TypeVesselDto>> res = null;
        List<TypeVessel> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeVesselDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeVessels")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeVesselDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeVesselDto>> res = null;
        List<TypeVessel> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeVesselDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeVessel by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeVesselDto> findById(@PathVariable Long id) {
        TypeVessel t = service.findById(id);
        if (t != null) {
            TypeVesselDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeVessel by label")
    @GetMapping("label/{label}")
    public ResponseEntity<TypeVesselDto> findByLabel(@PathVariable String label) {
	    TypeVessel t = service.findByReferenceEntity(new TypeVessel(label));
        if (t != null) {
            TypeVesselDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeVessel")
    @PostMapping("")
    public ResponseEntity<TypeVesselDto> save(@RequestBody TypeVesselDto dto) throws Exception {
        if(dto!=null){
            TypeVessel myT = converter.toItem(dto);
            TypeVessel t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeVesselDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeVessel")
    @PutMapping("")
    public ResponseEntity<TypeVesselDto> update(@RequestBody TypeVesselDto dto) throws Exception {
        ResponseEntity<TypeVesselDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeVessel t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeVessel updated = service.update(t);
            TypeVesselDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeVessel")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeVesselDto>> delete(@RequestBody List<TypeVesselDto> dtos) throws Exception {
        ResponseEntity<List<TypeVesselDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeVessel> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeVessel")
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


    @Operation(summary = "Finds a typeVessel and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeVesselDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeVessel loaded =  service.findWithAssociatedLists(id);
        TypeVesselDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeVessels by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeVesselDto>> findByCriteria(@RequestBody TypeVesselCriteria criteria) throws Exception {
        ResponseEntity<List<TypeVesselDto>> res = null;
        List<TypeVessel> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeVesselDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeVessels by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeVesselCriteria criteria) throws Exception {
        List<TypeVessel> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeVesselDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeVessel data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeVesselCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeVesselDto> findDtos(List<TypeVessel> list){
        List<TypeVesselDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeVesselDto> getDtoResponseEntity(TypeVesselDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TypeVesselRestAdmin(TypeVesselAdminService service, TypeVesselConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TypeVesselAdminService service;
    private final TypeVesselConverter converter;





}
