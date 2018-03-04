package jp.kodaira.boardgame.homepage.mapper

import jp.kodaira.boardgame.homepage.domain.ScheduleModel
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface ScheduleMapper {
    @Insert("""
        MERGE INTO schedule
        KEY
            (start_time)
        VALUES
            (#{startTime}, #{endTime}, #{summary}, #{location})
    """)
    fun upsert(scheduleModel: ScheduleModel)

    /**
     * scheduleを取得する
     */
    @Select("""
        SELECT
            *
        FROM
            schedule
    """)
    fun selectAll(): List<ScheduleModel>
}