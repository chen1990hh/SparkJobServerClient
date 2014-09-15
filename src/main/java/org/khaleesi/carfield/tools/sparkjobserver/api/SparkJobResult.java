package org.khaleesi.carfield.tools.sparkjobserver.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Presents the information of spark job result, when calling 
 * <code>GET /jobs/&lt;jobId&gt;</code> to a spark job server.
 * 
 * @author bluebreezecf
 * @since 2014-09-15
 *
 */
public class SparkJobResult extends SparkJobBaseInfo {

	private String result;
	private Map<String, Object> extendAttributes = new HashMap<String, Object>();

	SparkJobResult(String contents) {
		this.contents = contents;
	}
	public String getResult() {
		return result;
	}
	
	void setResult(String result) {
		this.result = result;
	}
	
	void putExtendAttribute(String key, Object value) {
		this.extendAttributes.put(key, value);
	}
	
	public Map<String, Object> getExtendAttributes() {
		return new HashMap<String, Object>(this.extendAttributes);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return contents;
//		StringBuffer buff = new StringBuffer("SparkJobResult{\n");
//		buff.append(" ").append(INFO_KEY_STATUS).append(": ")
//			.append(this.getStatus() != null ? this.getStatus() : INFO_EMPTY_VALUE).append(",\n");
//		if (containsAsynStatus()) {
//			setAsynStatusInfo(buff);
//		} else if (jobNotExists()) {
//			setNotExistsInfo(buff);
//		} else if (containsErrorInfo()) {
//			setErrorInfo(buff);
//		} 
//		if (containsExtendAttributes()) {
//			setExtendAttributesInfo(buff);
//		}
//		buff.append("}");
//		return buff.toString();
	}
	
	/**
	 * Judges current <code>SparkJobResult</code> instance represents the 
	 * status information of a asynchronous running spark job or not.
	 * 
	 * @return true indicates it contains asynchronous running status of a
	 *         spark job, false otherwise
	 */
	public boolean containsAsynStatus() {
		return SparkJobBaseInfo.INFO_STATUS_STARTED.equals(getStatus())
		    && getJobId() != null && getContext() != null;
	}

	/**
	 * Judges the queried target job doesn't exist or not.
	 * 
	 * @return true indicates the related job doesn't exist, false otherwise
	 */
	public boolean jobNotExists() {
		return SparkJobBaseInfo.INFO_STATUS_ERROR.equals(getStatus()) 
		    && getResult() != null && getResult().contains("No such job ID");
	}
	
	/**
	 * Judges current <code>SparkJobResult</code> instance contains 
	 * error information of a failed spark job or not.
	 * 
	 * @return true indicates it contains error message, false otherwise
	 */
	public boolean containsErrorInfo() {
		return SparkJobBaseInfo.INFO_STATUS_ERROR.equals(getStatus()) 
			&& getMessage() != null;
	}
	
	/**
	 * Judges current <code>SparkJobResult</code> instance contains 
	 * custom-defined extend attributes of result or not
	 * 
	 * @return true indicates it contains custom-defined extend attributes, false otherwise
	 */
	public boolean containsExtendAttributes() {
		return !extendAttributes.isEmpty();
	}
	
//	/**
//	 * Sets the status information of the asynchronous running job.
//	 * 
//	 * @param buff the existing contents
//	 */
//	private void setAsynStatusInfo(StringBuffer buff) {
//		if (buff != null) {
//			buff.append(" ").append(SparkJobBaseInfo.INFO_KEY_RESULT).append(": {\n")
//			    .append("  ").append(SparkJobBaseInfo.INFO_KEY_JOB_ID).append(": ").append(getJobId()).append(",\n")
//			    .append("  ").append(SparkJobBaseInfo.INFO_KEY_CONTEXT).append(": ").append(getContext()).append(",\n")
//			    .append(" }\n");
//		}
//	}
//	
//	/**
//	 * Sets the information for non-existence job.
//	 * 
//	 * @param buff the existing contents
//	 */
//	private void setNotExistsInfo(StringBuffer buff) {
//		if (buff != null) {
//			buff.append(" ").append(SparkJobBaseInfo.INFO_KEY_RESULT).append(": ")
//				.append(getResult()).append(",\n");
//		}
//	}
//	
//	/**
//	 * Sets the error information of the target failed job.
//	 * 
//	 * @param buff the existing contents
//	 */
//	private void setErrorInfo(StringBuffer buff) {
//		if (buff != null) {
//			buff.append(" ").append(SparkJobBaseInfo.INFO_STATUS_ERROR).append(": {\n")
//				.append("  ").append(SparkJobBaseInfo.INFO_KEY_RESULT_MESSAGE).append(": ").append(getMessage()).append(",\n");
//			if (getErrorClass() != null) {
//				buff.append("  ").append(SparkJobBaseInfo.INFO_KEY_RESULT_ERROR_CLASS).append(": ")
//				.append(getErrorClass()).append(",\n");
//			}
//			if (getStack() != null && getStack().length > 0) {
//				buff.append("  ").append(SparkJobBaseInfo.INFO_KEY_RESULT_STACK).append(": [");
//				for (String stackItem : getStack()) {
//					buff.append(stackItem).append(", ");
//				}
//				buff.append("]\n");
//			}
//		}
//	}
//	
//	/**
//	 * Sets the information of the extend attributes.
//	 * 
//	 * @param buff the existing contents
//	 */
//	private void setExtendAttributesInfo(StringBuffer buff) {
//		if (buff != null) {
//			Set<Entry<String, Object>> contents = extendAttributes.entrySet();
//			for (Entry<String, Object> item : contents) {
//				buff.append(" ").append(item.getKey()).append(": ")
//					.append(item.getValue().toString()).append(",\n");
//			}
//		}
//	}
}
