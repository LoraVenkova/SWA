package de.hse.swa.dbmodel;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sequence database table.
 * 
 */
@Entity
@NamedQuery(name="Sequence.findAll", query="SELECT s FROM Sequence s")
public class Sequence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="seq_count")
	private long seqCount;

	@Column(name="seq_name")
	private String seqName;

	private String sequencecol;

	public Sequence() {
	}

	public long getSeqCount() {
		return this.seqCount;
	}

	public void setSeqCount(long seqCount) {
		this.seqCount = seqCount;
	}

	public String getSeqName() {
		return this.seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public String getSequencecol() {
		return this.sequencecol;
	}

	public void setSequencecol(String sequencecol) {
		this.sequencecol = sequencecol;
	}

}