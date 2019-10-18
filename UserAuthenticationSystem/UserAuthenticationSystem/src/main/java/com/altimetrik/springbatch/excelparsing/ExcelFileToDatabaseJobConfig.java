package com.altimetrik.springbatch.excelparsing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import com.altimetrik.model.UserEntity;
import com.altimetrik.springbatch.common.UserProcessor;
import com.altimetrik.springbatch.common.UserWriter;


@Configuration
public class ExcelFileToDatabaseJobConfig {

    private static final String PROPERTY_EXCEL_SOURCE_FILE_PATH = "excel.to.database.job.source.file.path";

    @Bean
    ItemReader<UserEntity> excelUserReader(Environment environment) {
        PoiItemReader<UserEntity> reader = new PoiItemReader<>();
        reader.setLinesToSkip(1);
        reader.setResource(new ClassPathResource(environment.getRequiredProperty(PROPERTY_EXCEL_SOURCE_FILE_PATH)));
        reader.setRowMapper(excelRowMapper());
        return reader;
    }

    private RowMapper<UserEntity> excelRowMapper() {
        BeanWrapperRowMapper<UserEntity> rowMapper = new BeanWrapperRowMapper<>();
        rowMapper.setTargetType(UserEntity.class);
        return rowMapper;
    }

    /**
     * If Excel document has no header, below is the custom row mapper.
     */
    /*private RowMapper<UserEntity> excelRowMapper() {
       return new UserExcelRowMapper();
    }*/

    @Bean
    ItemProcessor<UserEntity, UserEntity> excelUserProcessor() {
        return new UserProcessor();
    }

    @Bean
    ItemWriter<UserEntity> excelUserWriter() {
        return new UserWriter();
    }

    @Bean
    Step excelFileToDatabaseStep(ItemReader<UserEntity> excelUserReader,
                                 ItemProcessor<UserEntity, UserEntity> excelUserProcessor,
                                 ItemWriter<UserEntity> excelUserWriter,
                                 StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("excelFileToDatabaseStep")
                .<UserEntity, UserEntity>chunk(1)
                .reader(excelUserReader)
                .processor(excelUserProcessor)
                .writer(excelUserWriter)
                .build();
    }

    @Bean
    Job excelFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,
                               @Qualifier("excelFileToDatabaseStep") Step excelUserStep) {
        return jobBuilderFactory.get("excelFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .flow(excelUserStep)
                .end()
                .build();
    }
}
