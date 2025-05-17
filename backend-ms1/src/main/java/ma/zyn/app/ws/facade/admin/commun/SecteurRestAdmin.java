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

import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.dao.criteria.core.commun.SecteurCriteria;
import ma.zyn.app.service.facade.admin.commun.SecteurAdminService;
import ma.zyn.app.ws.converter.commun.SecteurConverter;
import ma.zyn.app.ws.dto.commun.SecteurDto;
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
@RequestMapping("/api/admin/secteur/")
public class SecteurRestAdmin {




    @Operation(summary = "Finds a list of all secteurs")
    @GetMapping("")
    public ResponseEntity<List<SecteurDto>> findAll() throws Exception {
        ResponseEntity<List<SecteurDto>> res = null;
        List<Secteur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SecteurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all secteurs")
    @GetMapping("optimized")
    public ResponseEntity<List<SecteurDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<SecteurDto>> res = null;
        List<Secteur> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SecteurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a secteur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SecteurDto> findById(@PathVariable Long id) {
        Secteur t = service.findById(id);
        if (t != null) {
            SecteurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a secteur by label")
    @GetMapping("label/{label}")
    public ResponseEntity<SecteurDto> findByLabel(@PathVariable String label) {
	    Secteur t = service.findByReferenceEntity(new Secteur(label));
        if (t != null) {
            SecteurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  secteur")
    @PostMapping("")
    public ResponseEntity<SecteurDto> save(@RequestBody SecteurDto dto) throws Exception {
        if(dto!=null){
            Secteur myT = converter.toItem(dto);
            Secteur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SecteurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  secteur")
    @PutMapping("")
    public ResponseEntity<SecteurDto> update(@RequestBody SecteurDto dto) throws Exception {
        ResponseEntity<SecteurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Secteur t = service.findById(dto.getId());
            converter.copy(dto,t);
            Secteur updated = service.update(t);
            SecteurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of secteur")
    @PostMapping("multiple")
    public ResponseEntity<List<SecteurDto>> delete(@RequestBody List<SecteurDto> dtos) throws Exception {
        ResponseEntity<List<SecteurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Secteur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified secteur")
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


    @Operation(summary = "Finds a secteur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SecteurDto> findWithAssociatedLists(@PathVariable Long id) {
        Secteur loaded =  service.findWithAssociatedLists(id);
        SecteurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds secteurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SecteurDto>> findByCriteria(@RequestBody SecteurCriteria criteria) throws Exception {
        ResponseEntity<List<SecteurDto>> res = null;
        List<Secteur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SecteurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated secteurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SecteurCriteria criteria) throws Exception {
        List<Secteur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<SecteurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets secteur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SecteurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SecteurDto> findDtos(List<Secteur> list){
        List<SecteurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SecteurDto> getDtoResponseEntity(SecteurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public SecteurRestAdmin(SecteurAdminService service, SecteurConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final SecteurAdminService service;
    private final SecteurConverter converter;





}
