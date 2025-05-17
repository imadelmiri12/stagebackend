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

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteurDetail;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelSecteurDetailCriteria;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelSecteurDetailAdminService;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelSecteurDetailConverter;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelSecteurDetailDto;
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
@RequestMapping("/api/admin/rapportEvenementMensuelSecteurDetail/")
public class RapportEvenementMensuelSecteurDetailRestAdmin {




    @Operation(summary = "Finds a list of all rapportEvenementMensuelSecteurDetails")
    @GetMapping("")
    public ResponseEntity<List<RapportEvenementMensuelSecteurDetailDto>> findAll() throws Exception {
        ResponseEntity<List<RapportEvenementMensuelSecteurDetailDto>> res = null;
        List<RapportEvenementMensuelSecteurDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<RapportEvenementMensuelSecteurDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a rapportEvenementMensuelSecteurDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RapportEvenementMensuelSecteurDetailDto> findById(@PathVariable Long id) {
        RapportEvenementMensuelSecteurDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            RapportEvenementMensuelSecteurDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  rapportEvenementMensuelSecteurDetail")
    @PostMapping("")
    public ResponseEntity<RapportEvenementMensuelSecteurDetailDto> save(@RequestBody RapportEvenementMensuelSecteurDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            RapportEvenementMensuelSecteurDetail myT = converter.toItem(dto);
            RapportEvenementMensuelSecteurDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RapportEvenementMensuelSecteurDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  rapportEvenementMensuelSecteurDetail")
    @PutMapping("")
    public ResponseEntity<RapportEvenementMensuelSecteurDetailDto> update(@RequestBody RapportEvenementMensuelSecteurDetailDto dto) throws Exception {
        ResponseEntity<RapportEvenementMensuelSecteurDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RapportEvenementMensuelSecteurDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            RapportEvenementMensuelSecteurDetail updated = service.update(t);
            RapportEvenementMensuelSecteurDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of rapportEvenementMensuelSecteurDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<RapportEvenementMensuelSecteurDetailDto>> delete(@RequestBody List<RapportEvenementMensuelSecteurDetailDto> dtos) throws Exception {
        ResponseEntity<List<RapportEvenementMensuelSecteurDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<RapportEvenementMensuelSecteurDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified rapportEvenementMensuelSecteurDetail")
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

    @Operation(summary = "find by rapportEvenementMensuelSecteur id")
    @GetMapping("rapportEvenementMensuelSecteur/id/{id}")
    public List<RapportEvenementMensuelSecteurDetailDto> findByRapportEvenementMensuelSecteurId(@PathVariable Long id){
        return findDtos(service.findByRapportEvenementMensuelSecteurId(id));
    }
    @Operation(summary = "delete by rapportEvenementMensuelSecteur id")
    @DeleteMapping("rapportEvenementMensuelSecteur/id/{id}")
    public int deleteByRapportEvenementMensuelSecteurId(@PathVariable Long id){
        return service.deleteByRapportEvenementMensuelSecteurId(id);
    }
    @Operation(summary = "find by typeEvenement code")
    @GetMapping("typeEvenement/code/{code}")
    public List<RapportEvenementMensuelSecteurDetailDto> findByTypeEvenementCode(@PathVariable String code){
        return findDtos(service.findByTypeEvenementCode(code));
    }
    @Operation(summary = "delete by typeEvenement code")
    @DeleteMapping("typeEvenement/code/{code}")
    public int deleteByTypeEvenementCode(@PathVariable String code){
        return service.deleteByTypeEvenementCode(code);
    }

    @Operation(summary = "Finds a rapportEvenementMensuelSecteurDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RapportEvenementMensuelSecteurDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        RapportEvenementMensuelSecteurDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        RapportEvenementMensuelSecteurDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds rapportEvenementMensuelSecteurDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RapportEvenementMensuelSecteurDetailDto>> findByCriteria(@RequestBody RapportEvenementMensuelSecteurDetailCriteria criteria) throws Exception {
        ResponseEntity<List<RapportEvenementMensuelSecteurDetailDto>> res = null;
        List<RapportEvenementMensuelSecteurDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<RapportEvenementMensuelSecteurDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated rapportEvenementMensuelSecteurDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RapportEvenementMensuelSecteurDetailCriteria criteria) throws Exception {
        List<RapportEvenementMensuelSecteurDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<RapportEvenementMensuelSecteurDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets rapportEvenementMensuelSecteurDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RapportEvenementMensuelSecteurDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RapportEvenementMensuelSecteurDetailDto> findDtos(List<RapportEvenementMensuelSecteurDetail> list){
        converter.initObject(true);
        List<RapportEvenementMensuelSecteurDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RapportEvenementMensuelSecteurDetailDto> getDtoResponseEntity(RapportEvenementMensuelSecteurDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RapportEvenementMensuelSecteurDetailRestAdmin(RapportEvenementMensuelSecteurDetailAdminService service, RapportEvenementMensuelSecteurDetailConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final RapportEvenementMensuelSecteurDetailAdminService service;
    private final RapportEvenementMensuelSecteurDetailConverter converter;





}
