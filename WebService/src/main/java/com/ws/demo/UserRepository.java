
package com.ws.demo;


import com.ws.demo.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author desktop
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    
}
