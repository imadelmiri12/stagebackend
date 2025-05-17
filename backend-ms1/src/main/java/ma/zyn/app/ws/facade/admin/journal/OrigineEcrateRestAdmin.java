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

import ma.zyn.app.bean.core.journal.OrigineEcrate;
import ma.zyn.app.dao.criteria.core.journal.OrigineEcrateCriteria;
import ma.zyn.app.service.facade.admin.journal.OrigineEcrateAdminService;
import ma.zyn.app.ws.converter.journal.OrigineEcrateConverter;
import ma.zyn.app.ws.dto.journal.OrigineEcrateDto;
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
@RequestMapping("/api/admin/origineEcrate/")
public class OrigineEcrateRestAdmin {




    @Operation(summary = "Finds a list of all origineEcrates")
    @GetMapping("")
    public ResponseEntity<List<OrigineEcrateDto>> findAll() throws Exception {
        ResponseEntity<List<OrigineEcrateDto>> res = null;
        List<OrigineEcrate> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<OrigineEcrateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all origineEcrates")
    @GetMapping("optimized")
    public ResponseEntity<List<OrigineEcrateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<OrigineEcrateDto>> res = null;
        List<OrigineEcrate> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<OrigineEcrateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a origineEcrate by id")
    @GetMapping("id/{id}")
    public ResponseEntity<OrigineEcrateDto> findById(@PathVariable Long id) {
        OrigineEcrate t = service.findById(id);
        if (t != null) {
            OrigineEcrateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a origineEcrate by label")
    @GetMapping("label/{label}")
    public ResponseEntity<OrigineEcrateDto> findByLabel(@PathVariable String label) {
	    OrigineEcrate t = service.findByReferenceEntity(new OrigineEcrate(label));
        if (t != null) {
            OrigineEcrateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  origineEcrate")
    @PostMapping("")
    public ResponseEntity<OrigineEcrateDto> save(@RequestBody OrigineEcrateDto dto) throws Exception {
        if(dto!=null){
            OrigineEcrate myT = converter.toItem(dto);
            OrigineEcrate t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                OrigineEcrateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  origineEcrate")
    @PutMapping("")
    public ResponseEntity<OrigineEcrateDto> update(@RequestBody OrigineEcrateDto dto) throws Exception {
        ResponseEntity<OrigineEcrateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            OrigineEcrate t = service.findById(dto.getId());
            converter.copy(dto,t);
            OrigineEcrate updated = service.update(t);
            OrigineEcrateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of origineEcrate")
    @PostMapping("multiple")
    public ResponseEntity<List<OrigineEcrateDto>> delete(@RequestBody List<OrigineEcrateDto> dtos) throws Exception {
        ResponseEntity<List<OrigineEcrateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<OrigineEcrate> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified origineEcrate")
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


    @Operation(summary = "Finds a origineEcrate and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<OrigineEcrateDto> findWithAssociatedLists(@PathVariable Long id) {
        OrigineEcrate loaded =  service.findWithAssociatedLists(id);
        OrigineEcrateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds origineEcrates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<OrigineEcrateDto>> findByCriteria(@RequestBody OrigineEcrateCriteria criteria) throws Exception {
        ResponseEntity<List<OrigineEcrateDto>> res = null;
        List<OrigineEcrate> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<OrigineEcrateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated origineEcrates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody OrigineEcrateCriteria criteria) throws Exception {
        List<OrigineEcrate> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<OrigineEcrateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets origineEcrate data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody OrigineEcrateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<OrigineEcrateDto> findDtos(List<OrigineEcrate> list){
        List<OrigineEcrateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<OrigineEcrateDto> getDtoResponseEntity(OrigineEcrateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public OrigineEcrateRestAdmin(OrigineEcrateAdminService service, OrigineEcrateConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final OrigineEcrateAdminService service;
    private final OrigineEcrateConverter converter;





}
