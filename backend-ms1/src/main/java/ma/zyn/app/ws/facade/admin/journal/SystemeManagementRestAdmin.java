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

import ma.zyn.app.bean.core.journal.SystemeManagement;
import ma.zyn.app.dao.criteria.core.journal.SystemeManagementCriteria;
import ma.zyn.app.service.facade.admin.journal.SystemeManagementAdminService;
import ma.zyn.app.ws.converter.journal.SystemeManagementConverter;
import ma.zyn.app.ws.dto.journal.SystemeManagementDto;
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
@RequestMapping("/api/admin/systemeManagement/")
public class SystemeManagementRestAdmin {




    @Operation(summary = "Finds a list of all systemeManagements")
    @GetMapping("")
    public ResponseEntity<List<SystemeManagementDto>> findAll() throws Exception {
        ResponseEntity<List<SystemeManagementDto>> res = null;
        List<SystemeManagement> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SystemeManagementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all systemeManagements")
    @GetMapping("optimized")
    public ResponseEntity<List<SystemeManagementDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<SystemeManagementDto>> res = null;
        List<SystemeManagement> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SystemeManagementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a systemeManagement by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SystemeManagementDto> findById(@PathVariable Long id) {
        SystemeManagement t = service.findById(id);
        if (t != null) {
            SystemeManagementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a systemeManagement by label")
    @GetMapping("label/{label}")
    public ResponseEntity<SystemeManagementDto> findByLabel(@PathVariable String label) {
	    SystemeManagement t = service.findByReferenceEntity(new SystemeManagement(label));
        if (t != null) {
            SystemeManagementDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  systemeManagement")
    @PostMapping("")
    public ResponseEntity<SystemeManagementDto> save(@RequestBody SystemeManagementDto dto) throws Exception {
        if(dto!=null){
            SystemeManagement myT = converter.toItem(dto);
            SystemeManagement t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SystemeManagementDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  systemeManagement")
    @PutMapping("")
    public ResponseEntity<SystemeManagementDto> update(@RequestBody SystemeManagementDto dto) throws Exception {
        ResponseEntity<SystemeManagementDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            SystemeManagement t = service.findById(dto.getId());
            converter.copy(dto,t);
            SystemeManagement updated = service.update(t);
            SystemeManagementDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of systemeManagement")
    @PostMapping("multiple")
    public ResponseEntity<List<SystemeManagementDto>> delete(@RequestBody List<SystemeManagementDto> dtos) throws Exception {
        ResponseEntity<List<SystemeManagementDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<SystemeManagement> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified systemeManagement")
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


    @Operation(summary = "Finds a systemeManagement and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SystemeManagementDto> findWithAssociatedLists(@PathVariable Long id) {
        SystemeManagement loaded =  service.findWithAssociatedLists(id);
        SystemeManagementDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds systemeManagements by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SystemeManagementDto>> findByCriteria(@RequestBody SystemeManagementCriteria criteria) throws Exception {
        ResponseEntity<List<SystemeManagementDto>> res = null;
        List<SystemeManagement> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SystemeManagementDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated systemeManagements by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SystemeManagementCriteria criteria) throws Exception {
        List<SystemeManagement> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<SystemeManagementDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets systemeManagement data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SystemeManagementCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SystemeManagementDto> findDtos(List<SystemeManagement> list){
        List<SystemeManagementDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SystemeManagementDto> getDtoResponseEntity(SystemeManagementDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public SystemeManagementRestAdmin(SystemeManagementAdminService service, SystemeManagementConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final SystemeManagementAdminService service;
    private final SystemeManagementConverter converter;





}
