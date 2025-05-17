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

import ma.zyn.app.bean.core.mensuel.RapportEvenementMensuelSecteur;
import ma.zyn.app.dao.criteria.core.mensuel.RapportEvenementMensuelSecteurCriteria;
import ma.zyn.app.service.facade.admin.mensuel.RapportEvenementMensuelSecteurAdminService;
import ma.zyn.app.ws.converter.mensuel.RapportEvenementMensuelSecteurConverter;
import ma.zyn.app.ws.dto.mensuel.RapportEvenementMensuelSecteurDto;
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
@RequestMapping("/api/admin/rapportEvenementMensuelSecteur/")
public class RapportEvenementMensuelSecteurRestAdmin {




    @Operation(summary = "Finds a list of all rapportEvenementMensuelSecteurs")
    @GetMapping("")
    public ResponseEntity<List<RapportEvenementMensuelSecteurDto>> findAll() throws Exception {
        ResponseEntity<List<RapportEvenementMensuelSecteurDto>> res = null;
        List<RapportEvenementMensuelSecteur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<RapportEvenementMensuelSecteurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a rapportEvenementMensuelSecteur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RapportEvenementMensuelSecteurDto> findById(@PathVariable Long id) {
        RapportEvenementMensuelSecteur t = service.findById(id);
        if (t != null) {
            converter.init(true);
            RapportEvenementMensuelSecteurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  rapportEvenementMensuelSecteur")
    @PostMapping("")
    public ResponseEntity<RapportEvenementMensuelSecteurDto> save(@RequestBody RapportEvenementMensuelSecteurDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            RapportEvenementMensuelSecteur myT = converter.toItem(dto);
            RapportEvenementMensuelSecteur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                RapportEvenementMensuelSecteurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  rapportEvenementMensuelSecteur")
    @PutMapping("")
    public ResponseEntity<RapportEvenementMensuelSecteurDto> update(@RequestBody RapportEvenementMensuelSecteurDto dto) throws Exception {
        ResponseEntity<RapportEvenementMensuelSecteurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            RapportEvenementMensuelSecteur t = service.findById(dto.getId());
            converter.copy(dto,t);
            RapportEvenementMensuelSecteur updated = service.update(t);
            RapportEvenementMensuelSecteurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of rapportEvenementMensuelSecteur")
    @PostMapping("multiple")
    public ResponseEntity<List<RapportEvenementMensuelSecteurDto>> delete(@RequestBody List<RapportEvenementMensuelSecteurDto> dtos) throws Exception {
        ResponseEntity<List<RapportEvenementMensuelSecteurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<RapportEvenementMensuelSecteur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified rapportEvenementMensuelSecteur")
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


    @Operation(summary = "Finds a rapportEvenementMensuelSecteur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<RapportEvenementMensuelSecteurDto> findWithAssociatedLists(@PathVariable Long id) {
        RapportEvenementMensuelSecteur loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        RapportEvenementMensuelSecteurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds rapportEvenementMensuelSecteurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<RapportEvenementMensuelSecteurDto>> findByCriteria(@RequestBody RapportEvenementMensuelSecteurCriteria criteria) throws Exception {
        ResponseEntity<List<RapportEvenementMensuelSecteurDto>> res = null;
        List<RapportEvenementMensuelSecteur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<RapportEvenementMensuelSecteurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated rapportEvenementMensuelSecteurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody RapportEvenementMensuelSecteurCriteria criteria) throws Exception {
        List<RapportEvenementMensuelSecteur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<RapportEvenementMensuelSecteurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets rapportEvenementMensuelSecteur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody RapportEvenementMensuelSecteurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<RapportEvenementMensuelSecteurDto> findDtos(List<RapportEvenementMensuelSecteur> list){
        converter.initList(false);
        converter.initObject(true);
        List<RapportEvenementMensuelSecteurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<RapportEvenementMensuelSecteurDto> getDtoResponseEntity(RapportEvenementMensuelSecteurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public RapportEvenementMensuelSecteurRestAdmin(RapportEvenementMensuelSecteurAdminService service, RapportEvenementMensuelSecteurConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final RapportEvenementMensuelSecteurAdminService service;
    private final RapportEvenementMensuelSecteurConverter converter;





}
