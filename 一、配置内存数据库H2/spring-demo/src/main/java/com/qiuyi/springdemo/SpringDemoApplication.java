package com.qiuyi.springdemo;

        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.jdbc.core.JdbcTemplate;

        import javax.sql.DataSource;
        import java.sql.Connection;

@SpringBootApplication
@Slf4j
public class SpringDemoApplication implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        showConnection();
        showData();
    }

    /**
     * 打印数据库连接信息
     * @throws Exception
     */
    private void showConnection() throws Exception{
        log.info("dataSource＝" +dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.info("Connection=" + conn.toString());
        conn.close();
    }

    /**
     * 展示数据
     */
    private void showData(){
        jdbcTemplate
                .queryForList("select * from foo")
                .forEach(row -> log.info(row.toString()));
    }
}
