package Students;


import Students.Exceptions.BusinessException;
import Students.Repository.studentRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {
    @Inject
    studentRepository studentRepository;

    @GET
    public Response getAll(){
        List<Students> students = studentRepository.listAll();
        return Response.ok(students).build();

    }
    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") long id) throws BusinessException{
        Students student = studentRepository.findById(id);
        if (student==null){
           throw new BusinessException(Response.Status.NOT_FOUND.getStatusCode(), "the student with ID " + id + " is not exist in DB");
        }
        return Response.ok(student).build();
    }
    @GET
    @Path("FirstName/{FirstName}")
    public Response findbyFiestName(@PathParam("FirstName") String firstName) throws BusinessException{
        Students student = studentRepository.findByFirstName(firstName);
        if (student==null){
            throw new BusinessException(Response.Status.NOT_FOUND.getStatusCode(),"there is no student with this name in DB");
        }
        return Response.ok(student).build();
    }
    @GET
    @Path("Department/{Department}")
    public Response findbyDepartment(@PathParam("Department") String Department) throws BusinessException{
        List<Students> students = studentRepository.findByDepartment(Department);
        if (students.isEmpty()){
            throw new BusinessException(Response.Status.NOT_FOUND.getStatusCode(),"There is no student in this Department");
        }
        return Response.ok(students).build();
    }
    @POST
    @Transactional
    public Response CreateStudent(Students student) throws BusinessException {
        studentRepository.persist(student);
        if (studentRepository.isPersistent(student)){
            return Response.created(URI.create("/students/" + student.getId())).build();
        }
        throw new BusinessException(Response.Status.NOT_FOUND.getStatusCode()," Does not created ");
    }
    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteById(@PathParam("id") long id)throws BusinessException{
      boolean deleted= studentRepository.deleteById(id);
        if (deleted){
            return Response.noContent().build();
        }
                throw new BusinessException(Response.Status.NOT_FOUND.getStatusCode() , " there is no student with id " +id +" to be deleted");
    }
    @GET
    @Path("/Count")
    public long count(){
        return studentRepository.count();

    }


}
