package tsysSpring.sales.mapper;

import static com.github.springtestdbunit.annotation.DatabaseOperation.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import tsysSpring.sales.entity.Hotel;
import tsysSpring.sales.test.util.ExecuteQueryForTestService;

/**
 * クラスカテゴリテスト
 * テストID                  ：PT003_02
 * インターフェース名  ：HotelMapper
 * メソッド名               ：findHotelList
 *
 * @author FLM
 * @version 1.0 yyyy/mm/dd
 */
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:AllTest/setupTourDB.xml")
@DatabaseTearDown("classpath:AllTest/setupTourDB.xml")
@DisplayName("PT003_02:HotelMapper.findHotelList()メソッドのテスト")
public class HotelMapperfFindHotelListTest {

	@Autowired
	HotelMapper sut;

	@Autowired
	ExecuteQueryForTestService executeQueryForTestService;

	@Test
	@DisplayName("PT003_02_001：ホテル情報の検索が成功する場合")
	void testFindOne_test1() {
		// setup
		String testArg_date="2022/07";
		String testArg_cityCode ="01";

		List<Hotel> expected = List.of(
			new Hotel("HTL000183","札幌プリンセスホテル",15000,0,15,"HKD001","2022/07/02","01","札幌","5",15000),
			new Hotel("HTL000201","札幌プリンセスホテル",15000,0,15,"HKD001","2022/07/20","01","札幌","5",15000),
			new Hotel("HTL000203","札幌プリンセスホテル",15000,0,15,"HKD001","2022/07/22","01","札幌","5",15000),
			new Hotel("HTL000205","札幌プリンセスホテル",15000,0,15,"HKD001","2022/07/24","01","札幌","5",15000),
			new Hotel("HTL000207","札幌プリンセスホテル",15000,0,15,"HKD001","2022/07/26","01","札幌","5",15000),
			new Hotel("HTL000209","札幌プリンセスホテル",15000,0,15,"HKD001","2022/07/28","01","札幌","5",15000),
			new Hotel("HTL000211","札幌プリンセスホテル",15000,0,15,"HKD001","2022/07/30","01","札幌","5",15000));

		// assert
		assertThat(sut.findHotelList(testArg_date, testArg_cityCode)).isEqualTo(expected);

	}

	@Test
	@DisplayName("PT003_02_002：ホテル情報の検索が失敗する場合（存在しない宿泊年月）")
	void testFindOne_test2() {
		// setup
		String testArg_date="2022/12";
		String testArg_cityCode ="01";

		// assert
		assertThat(sut.findHotelList(testArg_date, testArg_cityCode)).isEmpty();

	}

	@Test
	@DisplayName("PT003_02_003：ホテル情報の検索が失敗する場合（存在しない都市コード）")
	void testFindOne_test3() {
		// setup
		String testArg_date="2022/07";
		String testArg_cityCode ="06";

		// assert
		assertThat(sut.findHotelList(testArg_date, testArg_cityCode)).isEmpty();

	}

	@Test
	@DatabaseSetup(type = DELETE_ALL)
	@DisplayName("PT003_02_004：ホテル情報の検索が失敗する場合（レコード0件）")
	void testFindOne_test4() {
		// setup
		String testArg_date="2022/07";
		String testArg_cityCode ="01";

		// assert
		assertThat(sut.findHotelList(testArg_date, testArg_cityCode)).isEmpty();
	}

	@Nested
	@SpringBootTest
	@DisplayName("PT003_02_005：DataAccessExceptionが発生する場合")
	class MemberTableRenamed {

		@BeforeEach
		void setUp() throws Exception {
			executeQueryForTestService.renameTable("hotel", "hotel2");
		}

		@AfterEach
		void tearDown() throws Exception {
			executeQueryForTestService.renameTable("hotel2", "hotel");
		}

		@Test
		@DisplayName("PT003_02_005：DataAccessExceptionが発生する場合")
		void testFindOne_test5() throws Exception {
			// setup
			String testArg_date="2022/07";
			String testArg_cityCode ="01";

			// assert
			assertThatThrownBy(() -> sut.findHotelList(testArg_date, testArg_cityCode))
				.isInstanceOf(DataAccessException.class);
		}
	}
}
