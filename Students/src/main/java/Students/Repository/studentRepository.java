package Students.Repository;

import Students.Students;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public class studentRepository implements PanacheRepository<Students> {

    public Students findByFirstName(String FirstName){
        return find("FirstName" , FirstName).firstResult();
    }
    public List<Students> findByDepartment(String Department){
        return this.list("Department" ,Sort.by("FirstName"), Department);
    }
    public PanacheQuery<Students> findByLastName(String lastName){
        return find("LastName" , lastName);
    }
    public void deleteByID(long id){
        delete("id" , id);
    }
}
