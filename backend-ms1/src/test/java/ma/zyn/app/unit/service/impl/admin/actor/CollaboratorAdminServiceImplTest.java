package ma.zyn.app.unit.service.impl.admin.actor;

import ma.zyn.app.bean.core.actor.Collaborator;
import ma.zyn.app.dao.facade.core.actor.CollaboratorDao;
import ma.zyn.app.service.impl.admin.actor.CollaboratorAdminServiceImpl;

import ma.zyn.app.bean.core.commun.TypeCollaborator ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class CollaboratorAdminServiceImplTest {

    @Mock
    private CollaboratorDao repository;
    private AutoCloseable autoCloseable;
    private CollaboratorAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new CollaboratorAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCollaborator() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveCollaborator() {
        // Given
        Collaborator toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteCollaborator() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetCollaboratorById() {
        // Given
        Long idToRetrieve = 1L; // Example Collaborator ID to retrieve
        Collaborator expected = new Collaborator(); // You need to replace Collaborator with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Collaborator result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Collaborator constructSample(int i) {
		Collaborator given = new Collaborator();
        given.setDescription("description-"+i);
        given.setTypeCollaborator(new TypeCollaborator(1L));
        given.setAccountNonExpired(false);
        given.setUsername("username-"+i);
        given.setPasswordChanged(false);
        given.setAccountNonLocked(false);
        given.setPassword("password-"+i);
        given.setEmail("email-"+i);
        given.setEnabled(false);
        given.setCredentialsNonExpired(false);
        return given;
    }

}
