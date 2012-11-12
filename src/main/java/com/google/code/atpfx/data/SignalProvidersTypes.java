package com.google.code.atpfx.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.code.atpfx.GlobalProperties;
import com.google.code.db.HibernateUtil;
import org.hibernate.Session;
import com.google.code.atpfx.data.SignalProvider;

public class SignalProvidersTypes {

	private static int NUMBER_OF_SIGNAL_PROVIDERS_TO_TEST = 1;
	private static ArrayList<SignalProvider> signalProvidersList = new ArrayList<SignalProvider>();
	private static HashMap<String, SignalProvider> signalProvidersByLabel = new HashMap<String, SignalProvider>();
	private static HashMap<Long, SignalProvider> signalProvidersById = new HashMap<Long, SignalProvider>();

	public SignalProvidersTypes() {
        if(GlobalProperties.isPersistenceEnabled()) {
            initDBSignalProvidersTypes(HibernateUtil.getSessionFactory().getCurrentSession());
        }
	}

	@SuppressWarnings("unchecked")
	private void initDBSignalProvidersTypes(Session session) {
		session.beginTransaction();
		ArrayList<SignalProvider> listOfConnectionTypes = new ArrayList<SignalProvider>(
				session.createQuery("from SignalProvider").list());
		for (SignalProvider c : listOfConnectionTypes) {
			signalProvidersList.add(c);
			signalProvidersByLabel.put(c.getLabel(), c);
			signalProvidersById.put(c.getId(), c);
		}
		session.getTransaction().commit();
	}

	public static SignalProvider getIdByName(String label) {
		return signalProvidersByLabel.get(label);
	}

	public static String getNameById(long id) {
		return signalProvidersById.get(id).getLabel();
	}

	public static ArrayList<SignalProvider> getSignalProvidersList() {
		ArrayList<SignalProvider> temp = new ArrayList<SignalProvider>();
		for (int j = 0; j < NUMBER_OF_SIGNAL_PROVIDERS_TO_TEST; j++)
			temp.add(signalProvidersList.get(j));
		return temp; // return signalProvidersList;
	}

}
