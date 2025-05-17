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

import ma.zyn.app.bean.core.journal.CategorieEcrate;
import ma.zyn.app.dao.criteria.core.journal.CategorieEcrateCriteria;
import ma.zyn.app.service.facade.admin.journal.CategorieEcrateAdminService;
import ma.zyn.app.ws.converter.journal.CategorieEcrateConverter;
import ma.zyn.app.ws.dto.journal.CategorieEcrateDto;
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
@RequestMapping("/api/admin/categorieEcrate/")
public class CategorieEcrateRestAdmin {




    @Operation(summary = "Finds a list of all categorieEcrates")
    @GetMapping("")
    public ResponseEntity<List<CategorieEcrateDto>> findAll() throws Exception {
        ResponseEntity<List<CategorieEcrateDto>> res = null;
        List<CategorieEcrate> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieEcrateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all categorieEcrates")
    @GetMapping("optimized")
    public ResponseEntity<List<CategorieEcrateDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CategorieEcrateDto>> res = null;
        List<CategorieEcrate> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieEcrateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a categorieEcrate by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategorieEcrateDto> findById(@PathVariable Long id) {
        CategorieEcrate t = service.findById(id);
        if (t != null) {
            CategorieEcrateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a categorieEcrate by label")
    @GetMapping("label/{label}")
    public ResponseEntity<CategorieEcrateDto> findByLabel(@PathVariable String label) {
	    CategorieEcrate t = service.findByReferenceEntity(new CategorieEcrate(label));
        if (t != null) {
            CategorieEcrateDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  categorieEcrate")
    @PostMapping("")
    public ResponseEntity<CategorieEcrateDto> save(@RequestBody CategorieEcrateDto dto) throws Exception {
        if(dto!=null){
            CategorieEcrate myT = converter.toItem(dto);
            CategorieEcrate t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CategorieEcrateDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  categorieEcrate")
    @PutMapping("")
    public ResponseEntity<CategorieEcrateDto> update(@RequestBody CategorieEcrateDto dto) throws Exception {
        ResponseEntity<CategorieEcrateDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CategorieEcrate t = service.findById(dto.getId());
            converter.copy(dto,t);
            CategorieEcrate updated = service.update(t);
            CategorieEcrateDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of categorieEcrate")
    @PostMapping("multiple")
    public ResponseEntity<List<CategorieEcrateDto>> delete(@RequestBody List<CategorieEcrateDto> dtos) throws Exception {
        ResponseEntity<List<CategorieEcrateDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CategorieEcrate> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified categorieEcrate")
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


    @Operation(summary = "Finds a categorieEcrate and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CategorieEcrateDto> findWithAssociatedLists(@PathVariable Long id) {
        CategorieEcrate loaded =  service.findWithAssociatedLists(id);
        CategorieEcrateDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds categorieEcrates by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CategorieEcrateDto>> findByCriteria(@RequestBody CategorieEcrateCriteria criteria) throws Exception {
        ResponseEntity<List<CategorieEcrateDto>> res = null;
        List<CategorieEcrate> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieEcrateDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated categorieEcrates by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CategorieEcrateCriteria criteria) throws Exception {
        List<CategorieEcrate> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CategorieEcrateDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets categorieEcrate data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CategorieEcrateCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CategorieEcrateDto> findDtos(List<CategorieEcrate> list){
        List<CategorieEcrateDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CategorieEcrateDto> getDtoResponseEntity(CategorieEcrateDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public CategorieEcrateRestAdmin(CategorieEcrateAdminService service, CategorieEcrateConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CategorieEcrateAdminService service;
    private final CategorieEcrateConverter converter;





}
