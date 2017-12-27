package qct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qct.retry.RetryConcurrentOperation;

/**
 * default description.
 *
 * @author qct
 * @date 2017/12/22
 * @since 1.0
 */
@Service
public class UpdateService2 {

    private static final Logger logger = LoggerFactory.getLogger(UpdateService2.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @RetryConcurrentOperation(exception = ConcurrencyFailureException.class, retries = 3)
    public Customer update(Customer c) {
        logger.debug("update 2 begin");
        Customer customer = customerRepository.findById(c.getId()).get();
        customer.setFirstName("update2");
        return customerRepository.save(customer);
    }
}
