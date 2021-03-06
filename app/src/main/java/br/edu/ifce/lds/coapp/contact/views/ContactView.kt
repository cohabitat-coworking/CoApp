package br.edu.ifce.lds.coapp.contact.views

import br.edu.ifce.lds.coapp.common.BaseView
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo

/**
 * Created by ellca on 06/06/2017.
 */

interface ContactView : BaseView {
    fun retrievedContactInfo(contactsInfo: LinkedHashMap<String, ContactInfo>)

    fun onError(message: String?)

    fun showLoading()

    fun hideLoading()

}
