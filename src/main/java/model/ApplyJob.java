package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApplyJob
{
	private int id, jobID, applicantID, applyJobStatusID;

	public ApplyJob()
	{
		super();
	}

	public ApplyJob(int id, int jobID, int applicantID, int applyJobStatusID)
	{
		super();
		this.id = id;
		this.jobID = jobID;
		this.applicantID = applicantID;
		this.applyJobStatusID = applyJobStatusID;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getJobID()
	{
		return jobID;
	}

	public void setJobID(int jobID)
	{
		this.jobID = jobID;
	}

	public int getApplicantID()
	{
		return applicantID;
	}

	public void setApplicantID(int applicantID)
	{
		this.applicantID = applicantID;
	}

	public int getApplyJobStatusID()
	{
		return applyJobStatusID;
	}

	public void setApplyJobStatusID(int applyJobStatusID)
	{
		this.applyJobStatusID = applyJobStatusID;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + applicantID;
		result = prime * result + applyJobStatusID;
		result = prime * result + id;
		result = prime * result + jobID;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplyJob other = (ApplyJob) obj;
		if (applicantID != other.applicantID)
			return false;
		if (applyJobStatusID != other.applyJobStatusID)
			return false;
		if (id != other.id)
			return false;
		if (jobID != other.jobID)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "ApplyJob [id=" + id + ", jobID=" + jobID + ", applicantID=" + applicantID + ", applyJobStatusID="
				+ applyJobStatusID + "]";
	}
}
