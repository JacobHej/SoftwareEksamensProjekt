package Applikationslag.Domaeneklasser;

public class AktivitetsOpsummering {
	private Aktivitet aktivitet;
	private int tid;
	
	public AktivitetsOpsummering(Aktivitet aktivitet, int tid)
	{
		this.aktivitet = aktivitet;
		this.tid = tid;
	}
	
	public Aktivitet Aktivitet()
	{
		return this.aktivitet;
	}
	
	public int Tid()
	{
		return this.tid;
	}
}
