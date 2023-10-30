package kopo.poly;

import kopo.poly.component.impl.Exam01;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j
@RequiredArgsConstructor
@SpringBootApplication
public class HadoopPrjApplication implements CommandLineRunner {
	private final Exam01 exam01
	@Override
	public void run(String... args) throws Exception {
		log.info("안녕하세요~~ 하둡 프로그래밍 실습!");

		log.info("첫 번째 실습");
		exam01.doExam();

		log.info("안녕하세요~~ 하둡 프로그래밍 실습 끝!");
	}

	public static void main(String[] args) {
		SpringApplication.run(HadoopPrjApplication.class, args);
	}

}
