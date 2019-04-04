package br.com.joffer.mango.features.payment.contacts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.joffer.mango.R
import br.com.joffer.mango.infra.utils.SpaceItemDecoration
import br.com.joffer.mango.infra.utils.dpToPx
import br.com.joffer.mango.infra.utils.gone
import br.com.joffer.mango.infra.utils.visible
import com.jakewharton.rxbinding3.widget.beforeTextChangeEvents
import com.jakewharton.rxbinding3.widget.textChangeEvents
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.view_search_view.*

class ContactsActivity: AppCompatActivity(), ContactsView{

    private val presenter: ContactsPresenter = ContactsPresenterImpl(this)
    private var diposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        diposable?.dispose()
    }

    override fun configuRecyclerView(contacts: List<Contact>){
        val adapter = ContactsAdapter(contacts)
        adapter.onClickListener = {

        }

        diposable = search_view.textChanges()
            .skipInitialValue()
            .subscribe { query -> adapter.filter.filter(query) }

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(SpaceItemDecoration(24.dpToPx()))

        val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        recycler_view.layoutAnimation = animation
    }

    override fun showLoading() {
        recycler_view?.gone()
        progress_bar.visible()
    }

    override fun hideLoading() {
        recycler_view?.visible()
        progress_bar.gone()
    }
}
