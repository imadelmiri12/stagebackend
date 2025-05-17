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

import ma.zyn.app.bean.core.journal.CritereEvaluation;
import ma.zyn.app.dao.criteria.core.journal.CritereEvaluationCriteria;
import ma.zyn.app.service.facade.admin.journal.CritereEvaluationAdminService;
import ma.zyn.app.ws.converter.journal.CritereEvaluationConverter;
import ma.zyn.app.ws.dto.journal.CritereEvaluationDto;
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
@RequestMapping("/api/admin/critereEvaluation/")
public class CritereEvaluationRestAdmin {




    @Operation(summary = "Finds a list of all critereEvaluations")
    @GetMapping("")
    public ResponseEntity<List<CritereEvaluationDto>> findAll() throws Exception {
        ResponseEntity<List<CritereEvaluationDto>> res = null;
        List<CritereEvaluation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CritereEvaluationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all critereEvaluations")
    @GetMapping("optimized")
    public ResponseEntity<List<CritereEvaluationDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CritereEvaluationDto>> res = null;
        List<CritereEvaluation> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CritereEvaluationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a critereEvaluation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CritereEvaluationDto> findById(@PathVariable Long id) {
        CritereEvaluation t = service.findById(id);
        if (t != null) {
            CritereEvaluationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a critereEvaluation by label")
    @GetMapping("label/{label}")
    public ResponseEntity<CritereEvaluationDto> findByLabel(@PathVariable String label) {
	    CritereEvaluation t = service.findByReferenceEntity(new CritereEvaluation(label));
        if (t != null) {
            CritereEvaluationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  critereEvaluation")
    @PostMapping("")
    public ResponseEntity<CritereEvaluationDto> save(@RequestBody CritereEvaluationDto dto) throws Exception {
        if(dto!=null){
            CritereEvaluation myT = converter.toItem(dto);
            CritereEvaluation t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CritereEvaluationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  critereEvaluation")
    @PutMapping("")
    public ResponseEntity<CritereEvaluationDto> update(@RequestBody CritereEvaluationDto dto) throws Exception {
        ResponseEntity<CritereEvaluationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CritereEvaluation t = service.findById(dto.getId());
            converter.copy(dto,t);
            CritereEvaluation updated = service.update(t);
            CritereEvaluationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of critereEvaluation")
    @PostMapping("multiple")
    public ResponseEntity<List<CritereEvaluationDto>> delete(@RequestBody List<CritereEvaluationDto> dtos) throws Exception {
        ResponseEntity<List<CritereEvaluationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CritereEvaluation> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified critereEvaluation")
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


    @Operation(summary = "Finds a critereEvaluation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CritereEvaluationDto> findWithAssociatedLists(@PathVariable Long id) {
        CritereEvaluation loaded =  service.findWithAssociatedLists(id);
        CritereEvaluationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds critereEvaluations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CritereEvaluationDto>> findByCriteria(@RequestBody CritereEvaluationCriteria criteria) throws Exception {
        ResponseEntity<List<CritereEvaluationDto>> res = null;
        List<CritereEvaluation> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CritereEvaluationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated critereEvaluations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CritereEvaluationCriteria criteria) throws Exception {
        List<CritereEvaluation> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CritereEvaluationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets critereEvaluation data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CritereEvaluationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CritereEvaluationDto> findDtos(List<CritereEvaluation> list){
        List<CritereEvaluationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CritereEvaluationDto> getDtoResponseEntity(CritereEvaluationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public CritereEvaluationRestAdmin(CritereEvaluationAdminService service, CritereEvaluationConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final CritereEvaluationAdminService service;
    private final CritereEvaluationConverter converter;





}
