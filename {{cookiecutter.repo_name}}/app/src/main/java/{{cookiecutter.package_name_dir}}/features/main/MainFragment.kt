package {{ cookiecutter.package_name }}.features.main

import android.os.Bundle
import android.view.View
import {{ cookiecutter.package_name }}.R
import {{ cookiecutter.package_name }}.base.BaseFragment
import {{ cookiecutter.package_name }}.databinding.FragmentMainBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvMainText.text = "Hello Text with view binding"
        // Do something else
    }
}
