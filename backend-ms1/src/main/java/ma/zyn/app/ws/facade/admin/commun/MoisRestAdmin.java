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

import ma.zyn.app.bean.core.commun.Mois;
import ma.zyn.app.dao.criteria.core.commun.MoisCriteria;
import ma.zyn.app.service.facade.admin.commun.MoisAdminService;
import ma.zyn.app.ws.converter.commun.MoisConverter;
import ma.zyn.app.ws.dto.commun.MoisDto;
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
@RequestMapping("/api/admin/mois/")
public class MoisRestAdmin {




    @Operation(summary = "Finds a list of all moiss")
    @GetMapping("")
    public ResponseEntity<List<MoisDto>> findAll() throws Exception {
        ResponseEntity<List<MoisDto>> res = null;
        List<Mois> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<MoisDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all moiss")
    @GetMapping("optimized")
    public ResponseEntity<List<MoisDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<MoisDto>> res = null;
        List<Mois> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<MoisDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a mois by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MoisDto> findById(@PathVariable Long id) {
        Mois t = service.findById(id);
        if (t != null) {
            converter.init(true);
            MoisDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a mois by label")
    @GetMapping("label/{label}")
    public ResponseEntity<MoisDto> findByLabel(@PathVariable String label) {
	    Mois t = service.findByReferenceEntity(new Mois(label));
        if (t != null) {
            converter.init(true);
            MoisDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  mois")
    @PostMapping("")
    public ResponseEntity<MoisDto> save(@RequestBody MoisDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Mois myT = converter.toItem(dto);
            Mois t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                MoisDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  mois")
    @PutMapping("")
    public ResponseEntity<MoisDto> update(@RequestBody MoisDto dto) throws Exception {
        ResponseEntity<MoisDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Mois t = service.findById(dto.getId());
            converter.copy(dto,t);
            Mois updated = service.update(t);
            MoisDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of mois")
    @PostMapping("multiple")
    public ResponseEntity<List<MoisDto>> delete(@RequestBody List<MoisDto> dtos) throws Exception {
        ResponseEntity<List<MoisDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Mois> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified mois")
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


    @Operation(summary = "Finds a mois and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MoisDto> findWithAssociatedLists(@PathVariable Long id) {
        Mois loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        MoisDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds moiss by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<MoisDto>> findByCriteria(@RequestBody MoisCriteria criteria) throws Exception {
        ResponseEntity<List<MoisDto>> res = null;
        List<Mois> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<MoisDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated moiss by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody MoisCriteria criteria) throws Exception {
        List<Mois> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<MoisDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets mois data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody MoisCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<MoisDto> findDtos(List<Mois> list){
        converter.initObject(true);
        List<MoisDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<MoisDto> getDtoResponseEntity(MoisDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public MoisRestAdmin(MoisAdminService service, MoisConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final MoisAdminService service;
    private final MoisConverter converter;





}
