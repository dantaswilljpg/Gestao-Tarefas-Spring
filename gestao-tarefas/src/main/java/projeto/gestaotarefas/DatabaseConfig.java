package projeto.gestaotarefas;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
import org.springframework.http.ResponseEntity;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/health");
        dataSource.setUsername("aluno");
        dataSource.setPassword("aluno");
        return dataSource;
    }

    @Bean
    public ResponseEntity<DataSource> dataSourceResponseEntity() {
        DataSource dataSource = dataSource();
        return ResponseEntity.ok(dataSource);
    }
}
