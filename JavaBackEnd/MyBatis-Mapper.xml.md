MyBatista Mapper XML文件模板



# MyBatisMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.hcakyle.mapper.XxxMapper">
    <!-- 表列名与实体属性的映射对应关系 -->
    <resultMap id="ColumnPropertyMap" type="com.taiping.tpbb.model.ExampleUser" >
        <!-- column：列名；property：Entity对应的属性名；jdbcType：该列对应的JDBC类型 -->
        <id column="id" property="id" jdbcType="BIGINT" />
        <result column="email" property="email" jdbcType="VARCHAR" />
        <result column="password" property="password" jdbcType="VARCHAR" />
        <result column="create_time" property="createTime" jdbcType="TIMESTAMP" />
        <result column="create_time" property="createTime" jdbcType="TIMESTAMP" />
        <result column="is_deleted" property="isDeleted" jdbcType="BOOLEAN" />
    </resultMap>


    <!-- 专门为插入所准备的类型 -->
    <sql id="Columns4Insert">
        id(不使用自增的情况), username, password, ... [不要有create_time, update_time, is_deleted等有默认值的字段]
    </sql>

    <!-- 所有的列名 -->
    <sql id="All_Columns">
        id, username, password, ...
    </sql>


    <!-- 
        增
     -->
    <insert id="insert" parameterType="com.hackyle.entity.XxxEntity"
        useGeneratedKeys="true" keyProperty="id" keyColumn="id" >
        -- 返回插入后的ID值
        -- <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Long">
        --     SELECT LAST_INSERT_ID()
        -- </selectKey>
        -- Notice: 
        -- 1.即使使用了useGeneratedKeys，执行insert后的方法返回任然是执行插入成功的行数
        -- 2.要获取刚刚插入的ID，应该设置插入POJO的ID属性，然后使用get方法获取刚刚插入的ID值

        INSERT INTO 表名
        <include refid="Columns4Insert" />
        values (#{id}, #{username}, #{password}, );
    </insert>

    <insert id="insertSelective" parameterType="com.hackyle.entity.XxxEntity" resultType="Long">
        -- 返回插入后的ID值
        <selectKey keyProperty="id" order="AFTER" resultType="java.lang.Long">
            SELECT LAST_INSERT_ID()
        </selectKey>
        
        INSERT INTO 表名
        <trim prefix="(" suffix=")" suffixOverrides="," >
            <if test="id != null and id != '' " >
                id,
            </if>
            <if test="username != null and username != '' " >
                username,
            </if>
            ....
        </trim>
        <trim prefix="VALUES (" suffix=")" suffixOverrides="," >
            <if test="id != null and id != '' " >
                #{id,jdbcType=BIGINT},
            </if>
            <if test="username != null and username != ''" >
                #{email,jdbcType=VARCHAR},
            </if>
            ....
        </trim>
    </insert>


    <!-- 
        删（逻辑删）
     -->
    <update id="delete">
        UPDATE 表名 SET is_deleted = 1 WHERE id = #{id};
    </update>


    <!-- 
        改
     -->
    <update id="updateByPrimaryKeySelective" parameterType="com.taiping.tpbb.model.ErgoPolicy">
        UPDATE 表名
        <set>
          <if test="username != null and username != ''">
            username = #{username, jdbcType=VARCHAR},
          </if>
          <if test="password != null and password != ''">
            password = #{password, jdbcType=VARCHAR},
          </if>
          ....
        </set>
        WHERE id = #{id, jdbcType=BIGINT}
    </update>


    <!-- 
        查
     -->
    <select id="selectById" resultMap="ColumnPropertyMap">
        SELECT
        <include refid="All_Columns" />
        FROM 表名
        WHERE id = #{id}
    </select>

    <select id="selectByParams" parameterType="com.hackyle.entity.XxxEntityQo" resultMap="ColumnPropertyMap">
        SELECT
        <include refid="All_Columns" />
        FROM 表名
        <where>
            <if test="usernmae != null and username != ''">
                username = #{username}
            </if>
        </where>
    </select>


</mapper>
```



# 关键字

foreach：循环遍历

trim：移除



## foreach

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.hcakyle.mapper.XxxMapper">
    
    <!-- IN子句查询Person: List<Person> readPerson(List<Integer> ids); -->
    <select id="readPerson" resultType="net.hackyle.entity.Person">
        SELECT * FROM person
        <where>
            id in
            <!-- foreach标签：遍历集合 -->
                <!-- collection：指定要遍历的集合 -->
                <!-- item：临时保存当前遍历出的元素，类似于for(int i=0; i<10; i++)中的i -->
                <!-- separator：每个元素之间的分割符 -->
                <!-- open：遍历出的所有结果拼接一个开始字符 -->
                <!-- close：遍历出的所有结果拼接一个结束符 -->
            <foreach collection="list" item="i" separator=","
                     open="(" close=")" >
                <!-- #{i}: 取出元素 -->
                #{i}
            </foreach>
        </where>
    </select>

    <!-- 批量保存: int createPersonByBatch(@Param("personList")List<Person> personList); -->
    <insert id="createPersonByBatch">
        INSERT INTO person(name,sex,birthday,address)
        VALUES
        <foreach collection="personList" item="person" separator=",">
            (#{person.name}, #{person.sex}, #{person.birthday}, #{person.address})
        </foreach>
    </insert>

</mapper>

```



## trim

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.hcakyle.mapper.XxxMapper">
    <!-- 根据性别查询：List<Person> readBySex(String sex); -->
    <select id="readBySex" resultType="net.hackyle.entity.Person">
        SELECT a.id, a.name, a.sex, a.birthday, a.address
            FROM person a
            
            <!-- trim标签： -->
                <!-- prefix：指定前缀。在整个trim标签体前面，拼接上一个指定的字符串 -->
                <!-- prefixOverrides：对于整个trim标签体内所管理的字符串，去掉在前面指定的字符串 -->
                <!-- suffix：指定后缀。在整个trim标签体后面，拼接上一个指定的字符串 -->
                <!-- suffixOverrides：对于整个trim标签体内所管理的字符串，去掉在后面指定的字符串 -->
            <trim prefix="where" suffixOverrides="and">
                <if test="sex != null and sex != '' ">
                    and a.sex=#{sex}
                </if>
            </trim>
    </select>
</mapper>

```











