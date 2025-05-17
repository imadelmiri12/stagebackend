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

import ma.zyn.app.bean.core.commun.Vessel;
import ma.zyn.app.dao.criteria.core.commun.VesselCriteria;
import ma.zyn.app.service.facade.admin.commun.VesselAdminService;
import ma.zyn.app.ws.converter.commun.VesselConverter;
import ma.zyn.app.ws.dto.commun.VesselDto;
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
@RequestMapping("/api/admin/vessel/")
public class VesselRestAdmin {




    @Operation(summary = "Finds a list of all vessels")
    @GetMapping("")
    public ResponseEntity<List<VesselDto>> findAll() throws Exception {
        ResponseEntity<List<VesselDto>> res = null;
        List<Vessel> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<VesselDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all vessels")
    @GetMapping("optimized")
    public ResponseEntity<List<VesselDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<VesselDto>> res = null;
        List<Vessel> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<VesselDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a vessel by id")
    @GetMapping("id/{id}")
    public ResponseEntity<VesselDto> findById(@PathVariable Long id) {
        Vessel t = service.findById(id);
        if (t != null) {
            VesselDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a vessel by label")
    @GetMapping("label/{label}")
    public ResponseEntity<VesselDto> findByLabel(@PathVariable String label) {
	    Vessel t = service.findByReferenceEntity(new Vessel(label));
        if (t != null) {
            VesselDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  vessel")
    @PostMapping("")
    public ResponseEntity<VesselDto> save(@RequestBody VesselDto dto) throws Exception {
        if(dto!=null){
            Vessel myT = converter.toItem(dto);
            Vessel t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                VesselDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  vessel")
    @PutMapping("")
    public ResponseEntity<VesselDto> update(@RequestBody VesselDto dto) throws Exception {
        ResponseEntity<VesselDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Vessel t = service.findById(dto.getId());
            converter.copy(dto,t);
            Vessel updated = service.update(t);
            VesselDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of vessel")
    @PostMapping("multiple")
    public ResponseEntity<List<VesselDto>> delete(@RequestBody List<VesselDto> dtos) throws Exception {
        ResponseEntity<List<VesselDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Vessel> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified vessel")
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


    @Operation(summary = "Finds a vessel and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<VesselDto> findWithAssociatedLists(@PathVariable Long id) {
        Vessel loaded =  service.findWithAssociatedLists(id);
        VesselDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds vessels by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<VesselDto>> findByCriteria(@RequestBody VesselCriteria criteria) throws Exception {
        ResponseEntity<List<VesselDto>> res = null;
        List<Vessel> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<VesselDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated vessels by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody VesselCriteria criteria) throws Exception {
        List<Vessel> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<VesselDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets vessel data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody VesselCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<VesselDto> findDtos(List<Vessel> list){
        List<VesselDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<VesselDto> getDtoResponseEntity(VesselDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public VesselRestAdmin(VesselAdminService service, VesselConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final VesselAdminService service;
    private final VesselConverter converter;





}
