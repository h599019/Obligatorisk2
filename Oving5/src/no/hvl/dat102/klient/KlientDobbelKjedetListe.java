package no.hvl.dat102.klient;

import no.hvl.dat102.kjedet.DobbelKjedetOrdnetListe;

public class KlientDobbelKjedetListe {

	public static void main(String[] args) {
		String ord[] = { "o", "a", "s", "m", "e", "k", "c" };

		DobbelKjedetOrdnetListe<String> liste = new DobbelKjedetOrdnetListe(new String("AAA"), new String("zzz"));
		// Klienten b�r sjekke p� at alle verdiene ligger innenfor grensene
		// Legger data inn i listen
		for (int i = 0; i < ord.length; i++) {
			liste.leggTil(ord[i]);

		}
		// Utskrift foran
		System.out.println(liste);

		// Utskrift bak
		System.out.println(liste.tilStrengBaklengs());

		// Tester om et bestem element fins
		String el = new String("m");
		boolean funnet = liste.fins(el);
		if (funnet)
			System.out.println("\nElementet " + el + " fins\n");
		else
			System.out.println("\nElementet " + el + " fins ikke\n");

		// slette m
		liste.fjern("m");

		// Utskrift etter sletting
		System.out.println(liste);

		// Tester om et bestem element fins
		String el1 = new String("m");
		boolean funnet1 = liste.fins(el);
		if (funnet1)
			System.out.println("\nElementet " + el1 + " fins\n");
		else
			System.out.println("\nElementet " + el1 + " fins ikke\n");

		// slette t som ikke fins
		liste.fjern("t");
		System.out.println(liste);

	}


}
