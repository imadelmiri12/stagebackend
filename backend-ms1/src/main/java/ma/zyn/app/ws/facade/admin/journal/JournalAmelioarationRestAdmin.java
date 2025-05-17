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

import ma.zyn.app.bean.core.journal.JournalAmelioaration;
import ma.zyn.app.dao.criteria.core.journal.JournalAmelioarationCriteria;
import ma.zyn.app.service.facade.admin.journal.JournalAmelioarationAdminService;
import ma.zyn.app.ws.converter.journal.JournalAmelioarationConverter;
import ma.zyn.app.ws.dto.journal.JournalAmelioarationDto;
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
@RequestMapping("/api/admin/journalAmelioaration/")
public class JournalAmelioarationRestAdmin {




    @Operation(summary = "Finds a list of all journalAmelioarations")
    @GetMapping("")
    public ResponseEntity<List<JournalAmelioarationDto>> findAll() throws Exception {
        ResponseEntity<List<JournalAmelioarationDto>> res = null;
        List<JournalAmelioaration> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<JournalAmelioarationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a journalAmelioaration by id")
    @GetMapping("id/{id}")
    public ResponseEntity<JournalAmelioarationDto> findById(@PathVariable Long id) {
        JournalAmelioaration t = service.findById(id);
        if (t != null) {
            converter.init(true);
            JournalAmelioarationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  journalAmelioaration")
    @PostMapping("")
    public ResponseEntity<JournalAmelioarationDto> save(@RequestBody JournalAmelioarationDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            JournalAmelioaration myT = converter.toItem(dto);
            JournalAmelioaration t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                JournalAmelioarationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  journalAmelioaration")
    @PutMapping("")
    public ResponseEntity<JournalAmelioarationDto> update(@RequestBody JournalAmelioarationDto dto) throws Exception {
        ResponseEntity<JournalAmelioarationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            JournalAmelioaration t = service.findById(dto.getId());
            converter.copy(dto,t);
            JournalAmelioaration updated = service.update(t);
            JournalAmelioarationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of journalAmelioaration")
    @PostMapping("multiple")
    public ResponseEntity<List<JournalAmelioarationDto>> delete(@RequestBody List<JournalAmelioarationDto> dtos) throws Exception {
        ResponseEntity<List<JournalAmelioarationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<JournalAmelioaration> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified journalAmelioaration")
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


    @Operation(summary = "Finds a journalAmelioaration and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<JournalAmelioarationDto> findWithAssociatedLists(@PathVariable Long id) {
        JournalAmelioaration loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        JournalAmelioarationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds journalAmelioarations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<JournalAmelioarationDto>> findByCriteria(@RequestBody JournalAmelioarationCriteria criteria) throws Exception {
        ResponseEntity<List<JournalAmelioarationDto>> res = null;
        List<JournalAmelioaration> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<JournalAmelioarationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated journalAmelioarations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody JournalAmelioarationCriteria criteria) throws Exception {
        List<JournalAmelioaration> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<JournalAmelioarationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets journalAmelioaration data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody JournalAmelioarationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<JournalAmelioarationDto> findDtos(List<JournalAmelioaration> list){
        converter.initObject(true);
        List<JournalAmelioarationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<JournalAmelioarationDto> getDtoResponseEntity(JournalAmelioarationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public JournalAmelioarationRestAdmin(JournalAmelioarationAdminService service, JournalAmelioarationConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final JournalAmelioarationAdminService service;
    private final JournalAmelioarationConverter converter;





}
