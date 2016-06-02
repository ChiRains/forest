package com.qcloud.component.my.dao.mysql.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qcloud.component.my.model.QuestionnaireAnswers;
import com.qcloud.component.my.model.query.QuestionnaireAnswersQuery;

public interface QuestionnaireAnswersMapper {

	@Insert("insert into `my_questionnaire_answers`(`id`,`userId`,`paper`,`time`,`questionnaireId`,`questionnaireName`,`questionId`,`questionName`,`answer`,`state`)"
			+ " values(#{id},#{userId},#{paper},#{time},#{questionnaireId},#{questionnaireName},#{questionId},#{questionName},#{answer},#{state})")
	public void insert(QuestionnaireAnswers questionnaireAnswers);

	@Select("select * from `my_questionnaire_answers` where `id`=#{id}")
	public QuestionnaireAnswers get(Long id);

	@Update("update `my_questionnaire_answers` set `userId`=#{userId},`paper`=#{paper},`time`=#{time},`questionnaireId`=#{questionnaireId},`questionnaireName`=#{questionnaireName},`questionId`=#{questionId},`questionName`=#{questionName},`answer`=#{answer},`state`=#{state} where `id`=#{id}")
	public void update(QuestionnaireAnswers questionnaireAnswers);

	@Delete("delete from `my_questionnaire_answers` where `id`=#{id}")
	public void delete(Long id);

	@Select("select * from `my_questionnaire_answers` limit #{start},#{count}")
	public List<QuestionnaireAnswers> list4page(Map<String,Object> param);

	@Select("select count(*) from `my_questionnaire_answers`")
	public int count4page(Map<String,Object> param);
	
	@Select("select * from `my_questionnaire_answers`")
	public List<QuestionnaireAnswers> listAll();
}