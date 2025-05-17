package  ma.zyn.app.ws.facade.admin.indicateur;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.indicateur.Trimestre;
import ma.zyn.app.dao.criteria.core.indicateur.TrimestreCriteria;
import ma.zyn.app.service.facade.admin.indicateur.TrimestreAdminService;
import ma.zyn.app.ws.converter.indicateur.TrimestreConverter;
import ma.zyn.app.ws.dto.indicateur.TrimestreDto;
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
@RequestMapping("/api/admin/trimestre/")
public class TrimestreRestAdmin {




    @Operation(summary = "Finds a list of all trimestres")
    @GetMapping("")
    public ResponseEntity<List<TrimestreDto>> findAll() throws Exception {
        ResponseEntity<List<TrimestreDto>> res = null;
        List<Trimestre> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TrimestreDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all trimestres")
    @GetMapping("optimized")
    public ResponseEntity<List<TrimestreDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TrimestreDto>> res = null;
        List<Trimestre> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TrimestreDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a trimestre by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TrimestreDto> findById(@PathVariable Long id) {
        Trimestre t = service.findById(id);
        if (t != null) {
            TrimestreDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a trimestre by label")
    @GetMapping("label/{label}")
    public ResponseEntity<TrimestreDto> findByLabel(@PathVariable String label) {
	    Trimestre t = service.findByReferenceEntity(new Trimestre(label));
        if (t != null) {
            TrimestreDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  trimestre")
    @PostMapping("")
    public ResponseEntity<TrimestreDto> save(@RequestBody TrimestreDto dto) throws Exception {
        if(dto!=null){
            Trimestre myT = converter.toItem(dto);
            Trimestre t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TrimestreDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  trimestre")
    @PutMapping("")
    public ResponseEntity<TrimestreDto> update(@RequestBody TrimestreDto dto) throws Exception {
        ResponseEntity<TrimestreDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Trimestre t = service.findById(dto.getId());
            converter.copy(dto,t);
            Trimestre updated = service.update(t);
            TrimestreDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of trimestre")
    @PostMapping("multiple")
    public ResponseEntity<List<TrimestreDto>> delete(@RequestBody List<TrimestreDto> dtos) throws Exception {
        ResponseEntity<List<TrimestreDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Trimestre> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified trimestre")
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


    @Operation(summary = "Finds a trimestre and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TrimestreDto> findWithAssociatedLists(@PathVariable Long id) {
        Trimestre loaded =  service.findWithAssociatedLists(id);
        TrimestreDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds trimestres by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TrimestreDto>> findByCriteria(@RequestBody TrimestreCriteria criteria) throws Exception {
        ResponseEntity<List<TrimestreDto>> res = null;
        List<Trimestre> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TrimestreDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated trimestres by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TrimestreCriteria criteria) throws Exception {
        List<Trimestre> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TrimestreDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets trimestre data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TrimestreCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TrimestreDto> findDtos(List<Trimestre> list){
        List<TrimestreDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TrimestreDto> getDtoResponseEntity(TrimestreDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public TrimestreRestAdmin(TrimestreAdminService service, TrimestreConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TrimestreAdminService service;
    private final TrimestreConverter converter;





}
