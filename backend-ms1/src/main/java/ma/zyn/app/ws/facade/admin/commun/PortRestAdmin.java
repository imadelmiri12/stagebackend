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

import ma.zyn.app.bean.core.commun.Port;
import ma.zyn.app.dao.criteria.core.commun.PortCriteria;
import ma.zyn.app.service.facade.admin.commun.PortAdminService;
import ma.zyn.app.ws.converter.commun.PortConverter;
import ma.zyn.app.ws.dto.commun.PortDto;
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
@RequestMapping("/api/admin/port/")
public class PortRestAdmin {




    @Operation(summary = "Finds a list of all ports")
    @GetMapping("")
    public ResponseEntity<List<PortDto>> findAll() throws Exception {
        ResponseEntity<List<PortDto>> res = null;
        List<Port> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PortDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all ports")
    @GetMapping("optimized")
    public ResponseEntity<List<PortDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PortDto>> res = null;
        List<Port> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PortDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a port by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PortDto> findById(@PathVariable Long id) {
        Port t = service.findById(id);
        if (t != null) {
            PortDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a port by label")
    @GetMapping("label/{label}")
    public ResponseEntity<PortDto> findByLabel(@PathVariable String label) {
	    Port t = service.findByReferenceEntity(new Port(label));
        if (t != null) {
            PortDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  port")
    @PostMapping("")
    public ResponseEntity<PortDto> save(@RequestBody PortDto dto) throws Exception {
        if(dto!=null){
            Port myT = converter.toItem(dto);
            Port t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PortDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  port")
    @PutMapping("")
    public ResponseEntity<PortDto> update(@RequestBody PortDto dto) throws Exception {
        ResponseEntity<PortDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Port t = service.findById(dto.getId());
            converter.copy(dto,t);
            Port updated = service.update(t);
            PortDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of port")
    @PostMapping("multiple")
    public ResponseEntity<List<PortDto>> delete(@RequestBody List<PortDto> dtos) throws Exception {
        ResponseEntity<List<PortDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Port> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified port")
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


    @Operation(summary = "Finds a port and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PortDto> findWithAssociatedLists(@PathVariable Long id) {
        Port loaded =  service.findWithAssociatedLists(id);
        PortDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds ports by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PortDto>> findByCriteria(@RequestBody PortCriteria criteria) throws Exception {
        ResponseEntity<List<PortDto>> res = null;
        List<Port> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PortDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated ports by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PortCriteria criteria) throws Exception {
        List<Port> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PortDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets port data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PortCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PortDto> findDtos(List<Port> list){
        List<PortDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PortDto> getDtoResponseEntity(PortDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public PortRestAdmin(PortAdminService service, PortConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final PortAdminService service;
    private final PortConverter converter;





}
