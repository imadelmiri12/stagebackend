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

import ma.zyn.app.bean.core.journal.ResultatEvaluation;
import ma.zyn.app.dao.criteria.core.journal.ResultatEvaluationCriteria;
import ma.zyn.app.service.facade.admin.journal.ResultatEvaluationAdminService;
import ma.zyn.app.ws.converter.journal.ResultatEvaluationConverter;
import ma.zyn.app.ws.dto.journal.ResultatEvaluationDto;
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
@RequestMapping("/api/admin/resultatEvaluation/")
public class ResultatEvaluationRestAdmin {




    @Operation(summary = "Finds a list of all resultatEvaluations")
    @GetMapping("")
    public ResponseEntity<List<ResultatEvaluationDto>> findAll() throws Exception {
        ResponseEntity<List<ResultatEvaluationDto>> res = null;
        List<ResultatEvaluation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ResultatEvaluationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all resultatEvaluations")
    @GetMapping("optimized")
    public ResponseEntity<List<ResultatEvaluationDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ResultatEvaluationDto>> res = null;
        List<ResultatEvaluation> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ResultatEvaluationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a resultatEvaluation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ResultatEvaluationDto> findById(@PathVariable Long id) {
        ResultatEvaluation t = service.findById(id);
        if (t != null) {
            ResultatEvaluationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a resultatEvaluation by label")
    @GetMapping("label/{label}")
    public ResponseEntity<ResultatEvaluationDto> findByLabel(@PathVariable String label) {
	    ResultatEvaluation t = service.findByReferenceEntity(new ResultatEvaluation(label));
        if (t != null) {
            ResultatEvaluationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  resultatEvaluation")
    @PostMapping("")
    public ResponseEntity<ResultatEvaluationDto> save(@RequestBody ResultatEvaluationDto dto) throws Exception {
        if(dto!=null){
            ResultatEvaluation myT = converter.toItem(dto);
            ResultatEvaluation t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ResultatEvaluationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  resultatEvaluation")
    @PutMapping("")
    public ResponseEntity<ResultatEvaluationDto> update(@RequestBody ResultatEvaluationDto dto) throws Exception {
        ResponseEntity<ResultatEvaluationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ResultatEvaluation t = service.findById(dto.getId());
            converter.copy(dto,t);
            ResultatEvaluation updated = service.update(t);
            ResultatEvaluationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of resultatEvaluation")
    @PostMapping("multiple")
    public ResponseEntity<List<ResultatEvaluationDto>> delete(@RequestBody List<ResultatEvaluationDto> dtos) throws Exception {
        ResponseEntity<List<ResultatEvaluationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ResultatEvaluation> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified resultatEvaluation")
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


    @Operation(summary = "Finds a resultatEvaluation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ResultatEvaluationDto> findWithAssociatedLists(@PathVariable Long id) {
        ResultatEvaluation loaded =  service.findWithAssociatedLists(id);
        ResultatEvaluationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds resultatEvaluations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ResultatEvaluationDto>> findByCriteria(@RequestBody ResultatEvaluationCriteria criteria) throws Exception {
        ResponseEntity<List<ResultatEvaluationDto>> res = null;
        List<ResultatEvaluation> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ResultatEvaluationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated resultatEvaluations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ResultatEvaluationCriteria criteria) throws Exception {
        List<ResultatEvaluation> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ResultatEvaluationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets resultatEvaluation data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ResultatEvaluationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ResultatEvaluationDto> findDtos(List<ResultatEvaluation> list){
        List<ResultatEvaluationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ResultatEvaluationDto> getDtoResponseEntity(ResultatEvaluationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ResultatEvaluationRestAdmin(ResultatEvaluationAdminService service, ResultatEvaluationConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ResultatEvaluationAdminService service;
    private final ResultatEvaluationConverter converter;





}
