package org.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringMultiDatasourceTest {

    private static Logger logger = LoggerFactory.getLogger(SpringMultiDatasourceTest.class);

    @Autowired
    @Qualifier("jdbcPrimaryTemplate")
    private JdbcTemplate jdbcPrinaryTemplate;

    @Autowired
    @Qualifier("jdbcSecondaryTemplate")
    private JdbcTemplate jdbcSecondaryTemplate;

    @Scheduled(fixedRate = 5000)
    public void testQuery(){

        Integer primaryCount = jdbcPrinaryTemplate.queryForObject("SELECT count(*) FROM cee.fe_prod_claims_postprocess",
                Integer.class);

        Integer secondaryCount = jdbcSecondaryTemplate.queryForObject("SELECT count(*) FROM cee.fe_prod_claims_postprocess",
                Integer.class);

        logger.debug("PrinaryCount {}, SecondaryCount {}", primaryCount, secondaryCount);
    }
}
