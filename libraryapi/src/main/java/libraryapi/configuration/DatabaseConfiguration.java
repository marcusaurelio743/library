package libraryapi.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {
	@Value("${spring.datasource.url}")
	String url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	@Value("${spring.datasource.driver-class-name}")
	String drive;
	
	//@Bean
	public DataSource datasource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setUrl(url);
		ds.setDriverClassName(drive);
		
		return ds;
	}
	
	@Bean
	public DataSource hikariDatasource() {
		HikariConfig config = new HikariConfig();
		config.setUsername(username);
		config.setPassword(password);
		config.setDriverClassName(drive);
		config.setJdbcUrl(url);
		
		config.setMaximumPoolSize(10);//maximo de configuracoes liberadas
		config.setMinimumIdle(1);//tamanho minimo de conexoes
		config.setPoolName("pool_confi_Lib");
		config.setMaxLifetime(600000);// 10minu de tempo de conexao
		config.setConnectionTimeout(100000);
		config.setConnectionTestQuery("select 1");
		
		return new HikariDataSource(config);
	}

}
