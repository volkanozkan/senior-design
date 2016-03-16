package model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Job
{
	private int id, jobSubjectID, customerID, paymentMethodID, jobStatusID;
	private String jobSubjectName, customerName, paymentMethodName, jobStatusName, title, descriptipn, imageURL, city;
	private Date startDate, endDate;
	private double price;
	private char isActive;

	public Job()
	{
		super();
	}

	public Job(int id, int jobSubjectID, int customerID, int paymentMethodID, int jobStatusID, String jobSubjectName,
			String customerName, String paymentMethodName, String jobStatusName, String title, String descriptipn,
			String imageURL, String city, Date startDate, Date endDate, double price, char isActive)
	{
		super();
		this.id = id;
		this.jobSubjectID = jobSubjectID;
		this.customerID = customerID;
		this.paymentMethodID = paymentMethodID;
		this.jobStatusID = jobStatusID;
		this.jobSubjectName = jobSubjectName;
		this.customerName = customerName;
		this.paymentMethodName = paymentMethodName;
		this.jobStatusName = jobStatusName;
		this.title = title;
		this.descriptipn = descriptipn;
		this.imageURL = imageURL;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.isActive = isActive;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getJobSubjectID()
	{
		return jobSubjectID;
	}

	public void setJobSubjectID(int jobSubjectID)
	{
		this.jobSubjectID = jobSubjectID;
	}

	public int getCustomerID()
	{
		return customerID;
	}

	public void setCustomerID(int customerID)
	{
		this.customerID = customerID;
	}

	public int getPaymentMethodID()
	{
		return paymentMethodID;
	}

	public void setPaymentMethodID(int paymentMethodID)
	{
		this.paymentMethodID = paymentMethodID;
	}

	public int getJobStatusID()
	{
		return jobStatusID;
	}

	public void setJobStatusID(int jobStatusID)
	{
		this.jobStatusID = jobStatusID;
	}

	public String getJobSubjectName()
	{
		return jobSubjectName;
	}

	public void setJobSubjectName(String jobSubjectName)
	{
		this.jobSubjectName = jobSubjectName;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public String getPaymentMethodName()
	{
		return paymentMethodName;
	}

	public void setPaymentMethodName(String paymentMethodName)
	{
		this.paymentMethodName = paymentMethodName;
	}

	public String getJobStatusName()
	{
		return jobStatusName;
	}

	public void setJobStatusName(String jobStatusName)
	{
		this.jobStatusName = jobStatusName;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescriptipn()
	{
		return descriptipn;
	}

	public void setDescriptipn(String descriptipn)
	{
		this.descriptipn = descriptipn;
	}

	public String getImageURL()
	{
		return imageURL;
	}

	public void setImageURL(String imageURL)
	{
		this.imageURL = imageURL;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public char getIsActive()
	{
		return isActive;
	}

	public void setIsActive(char isActive)
	{
		this.isActive = isActive;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + customerID;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((descriptipn == null) ? 0 : descriptipn.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((imageURL == null) ? 0 : imageURL.hashCode());
		result = prime * result + isActive;
		result = prime * result + jobStatusID;
		result = prime * result + ((jobStatusName == null) ? 0 : jobStatusName.hashCode());
		result = prime * result + jobSubjectID;
		result = prime * result + ((jobSubjectName == null) ? 0 : jobSubjectName.hashCode());
		result = prime * result + paymentMethodID;
		result = prime * result + ((paymentMethodName == null) ? 0 : paymentMethodName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Job other = (Job) obj;
		if (city == null)
		{
			if (other.city != null)
				return false;
		}
		else if (!city.equals(other.city))
			return false;
		if (customerID != other.customerID)
			return false;
		if (customerName == null)
		{
			if (other.customerName != null)
				return false;
		}
		else if (!customerName.equals(other.customerName))
			return false;
		if (descriptipn == null)
		{
			if (other.descriptipn != null)
				return false;
		}
		else if (!descriptipn.equals(other.descriptipn))
			return false;
		if (endDate == null)
		{
			if (other.endDate != null)
				return false;
		}
		else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (imageURL == null)
		{
			if (other.imageURL != null)
				return false;
		}
		else if (!imageURL.equals(other.imageURL))
			return false;
		if (isActive != other.isActive)
			return false;
		if (jobStatusID != other.jobStatusID)
			return false;
		if (jobStatusName == null)
		{
			if (other.jobStatusName != null)
				return false;
		}
		else if (!jobStatusName.equals(other.jobStatusName))
			return false;
		if (jobSubjectID != other.jobSubjectID)
			return false;
		if (jobSubjectName == null)
		{
			if (other.jobSubjectName != null)
				return false;
		}
		else if (!jobSubjectName.equals(other.jobSubjectName))
			return false;
		if (paymentMethodID != other.paymentMethodID)
			return false;
		if (paymentMethodName == null)
		{
			if (other.paymentMethodName != null)
				return false;
		}
		else if (!paymentMethodName.equals(other.paymentMethodName))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (startDate == null)
		{
			if (other.startDate != null)
				return false;
		}
		else if (!startDate.equals(other.startDate))
			return false;
		if (title == null)
		{
			if (other.title != null)
				return false;
		}
		else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Job [id=" + id + ", jobSubjectID=" + jobSubjectID + ", customerID=" + customerID + ", paymentMethodID="
				+ paymentMethodID + ", jobStatusID=" + jobStatusID + ", jobSubjectName=" + jobSubjectName
				+ ", customerName=" + customerName + ", paymentMethodName=" + paymentMethodName + ", jobStatusName="
				+ jobStatusName + ", title=" + title + ", descriptipn=" + descriptipn + ", imageURL=" + imageURL
				+ ", city=" + city + ", startDate=" + startDate + ", endDate=" + endDate + ", price=" + price
				+ ", isActive=" + isActive + "]";
	}

}
