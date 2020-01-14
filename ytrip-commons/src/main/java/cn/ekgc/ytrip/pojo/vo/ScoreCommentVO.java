package cn.ekgc.ytrip.pojo.vo;

import java.io.Serializable;

/**
 * <b>酒店各类评分VO</b>
 */
public class ScoreCommentVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Double avgPositionScore;//点评查询页面酒店的位置得分
	private Double avgFacilitiesScore;//点评查询页面酒店的设施得分
	private Double avgServiceScore;//点评查询页面酒店的服务得分
	private Double avgHygieneScore;//点评查询页面酒店的卫生得分
	private Double avgScore;//点评查询页面酒店的总体得分
	public Double getAvgPositionScore() {
		return avgPositionScore;
	}
	public void setAvgPositionScore(Double avgPositionScore) {
		this.avgPositionScore = avgPositionScore;
	}
	public Double getAvgFacilitiesScore() {
		return avgFacilitiesScore;
	}
	public void setAvgFacilitiesScore(Double avgFacilitiesScore) {
		this.avgFacilitiesScore = avgFacilitiesScore;
	}
	public Double getAvgServiceScore() {
		return avgServiceScore;
	}
	public void setAvgServiceScore(Double avgServiceScore) {
		this.avgServiceScore = avgServiceScore;
	}
	public Double getAvgHygieneScore() {
		return avgHygieneScore;
	}
	public void setAvgHygieneScore(Double avgHygieneScore) {
		this.avgHygieneScore = avgHygieneScore;
	}
	public Double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}
	
}
