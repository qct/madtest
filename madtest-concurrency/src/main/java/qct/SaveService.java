package qct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * default description.
 *
 * @author qct
 * @date 2017/12/26
 * @since 1.0
 */
@Service
@Transactional
public class SaveService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveOne(Customer customer) {
        return customerRepository.save(customer);
    }
}
