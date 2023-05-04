package com.zzb.model.vo;

import lombok.Data;

@Data
public class SysOperLogQueryVo {

	private String title;
	private String operName;

	private String createTimeBegin;
	private String createTimeEnd;

}

