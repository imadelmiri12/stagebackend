package  ma.zyn.app.ws.facade.admin.mensuel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuel;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelCriteria;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelAdminService;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelConverter;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelDto;
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
@RequestMapping("/api/admin/rapportEvenementMensuel/")
public class RapportEvenementMensuelRestAdmin {




    @Operation(summary = "Finds a list of all rapportEvenementMensuels")
    @GetMapping("")
    public ResponseEntity<List<RapportEvenementMensuelDto>> findAll() throws Exception {
        ResponseEntity<List<RapportEvenementMensuelDto>> res = null;
        List<RapportEvenementMensuel> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<RapportEvenementMensuelDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all rapportEvenementMensuels")
    @GetMapping("optimized")
    public ResponseEntity<List<RapportEvenementMensuelDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<RapportEvenementMensuelDto>> res = null;
        List<RapportEvenementMensuel> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<RapportEvenementMensuelDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a rapportEvenementMensuel by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RapportEvenementMensuelDto> findById(@PathVariable Long id) {
        RapportEvenementMensuel t = service.findById(id);
        if (t != null) {
            converter.init(true);
            RapportEvenementMensuelDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a rapportEvenementMensuel by code")
    @GetMapping("code/{code}")
    public ResponseEntity<RapportEvenementMensuelDto> findByCode(@PathVariable String code) {
	    RapportEvenementMensuel t = service.findByReferenceEntity(new RapportEvenementMensuel(code));
        if (t != null) {
            converter.init(true);
            RapportEvenementMensuelDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  rapportEvenementMensuel")
    @PostMapping("")
    public ResponseEntity<RapportEvenementMensuelDto> save(@RequestBody RapportEvenementMensuelDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            RapportEvenementMensuel myT = converter.toItem(dto);
            RapportEvenementMensuel t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RapportEvenementMensuelDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  rapportEvenementMensuel")
    @PutMapping("")
    public ResponseEntity<RapportEvenementMensuelDto> update(@RequestBody RapportEvenementMensuelDto dto) throws Exception {
        ResponseEntity<RapportEvenementMensuelDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RapportEvenementMensuel t = service.findById(dto.getId());
            converter.copy(dto,t);
            RapportEvenementMensuel updated = service.update(t);
            RapportEvenementMensuelDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of rapportEvenementMensuel")
    @PostMapping("multiple")
    public ResponseEntity<List<RapportEvenementMensuelDto>> delete(@RequestBody List<RapportEvenementMensuelDto> dtos) throws Exception {
        ResponseEntity<List<RapportEvenementMensuelDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<RapportEvenementMensuel> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified rapportEvenementMensuel")
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


    @Operation(summary = "Finds a rapportEvenementMensuel and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RapportEvenementMensuelDto> findWithAssociatedLists(@PathVariable Long id) {
        RapportEvenementMensuel loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        RapportEvenementMensuelDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds rapportEvenementMensuels by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RapportEvenementMensuelDto>> findByCriteria(@RequestBody RapportEvenementMensuelCriteria criteria) throws Exception {
        ResponseEntity<List<RapportEvenementMensuelDto>> res = null;
        List<RapportEvenementMensuel> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        List<RapportEvenementMensuelDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated rapportEvenementMensuels by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RapportEvenementMensuelCriteria criteria) throws Exception {
        List<RapportEvenementMensuel> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        List<RapportEvenementMensuelDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets rapportEvenementMensuel data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RapportEvenementMensuelCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RapportEvenementMensuelDto> findDtos(List<RapportEvenementMensuel> list){
        converter.initList(false);
        List<RapportEvenementMensuelDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RapportEvenementMensuelDto> getDtoResponseEntity(RapportEvenementMensuelDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RapportEvenementMensuelRestAdmin(RapportEvenementMensuelAdminService service, RapportEvenementMensuelConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final RapportEvenementMensuelAdminService service;
    private final RapportEvenementMensuelConverter converter;





}
