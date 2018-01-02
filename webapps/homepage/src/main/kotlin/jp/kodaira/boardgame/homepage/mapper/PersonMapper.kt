package jp.kodaira.boardgame.homepage.mapper

import jp.kodaira.boardgame.homepage.domain.Person
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select

@Mapper
interface PersonMapper {
    @Insert("""
        INSERT INTO person
            (name)
        VALUES
            (#{name})
    """)
    @Options(useGeneratedKeys = true)
    fun insert(person: Person)

    @Select("""
        SELECT
            *
        FROM
            person
        WHERE
            id = #{id}
    """)
    fun select(id: Int): Person

    /**
     * personを1000個取得する(1000を超えて必要ならその時考える)
     */
    @Select("""
        SELECT
            *
        FROM
            person
        LIMIT
            1000
    """)
    fun selectThousand(): List<Person>
}