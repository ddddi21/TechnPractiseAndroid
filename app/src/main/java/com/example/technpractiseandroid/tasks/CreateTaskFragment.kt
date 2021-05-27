package com.example.technpractiseandroid.tasks

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.technpractiseandroid.MyMainApplication
import com.example.technpractiseandroid.R
import com.example.technpractiseandroid.base.BaseFragment
import com.example.technpractiseandroid.databinding.CreateTaskFragmentBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

class CreateTaskFragment : BaseFragment<CreateTaskVM>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var createTaskVM: CreateTaskVM

    var dateAndTime = Calendar.getInstance()
    var currentDateTime: TextView? = null


    var tagWork: Button? = null
    var tagHome: Button? = null
    var tagFamily: Button? = null
    var tagMeeting: Button? = null
    var tagHealth: Button? = null
    var tagFun: Button? = null
    var tag: Button? = null

    var tagFast: Button? = null
    var tagMedium: Button? = null
    var tagCanWait: Button? = null
    var tagW: Button? = null


    // установка обработчика выбора времени
    var timeSetListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            dateAndTime.set(Calendar.MINUTE, minute)
            setInitialDateTime()
        }

    // установка обработчика выбора даты
    var dateSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateAndTime.set(Calendar.YEAR, year)
            dateAndTime.set(Calendar.MONTH, monthOfYear)
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setInitialDateTime()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        MyMainApplication.appComponent.inject(this)
        createTaskVM = ViewModelProvider(this, viewModelFactory).get(CreateTaskVM::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = CreateTaskFragmentBinding.inflate(inflater, container, false)
        currentDateTime = binding.currentDate
        binding.lifecycleOwner = this
        binding.vm = createTaskVM

        val randomActionName = arguments?.getString("actionName")
        val randomActionDesc = arguments?.getString("actionDesc")

        if(!(randomActionDesc.isNullOrEmpty() && randomActionName.isNullOrEmpty())){
            createTaskVM.taskName.value = randomActionDesc
            createTaskVM.taskDescription.value = randomActionName
            Log.d("find bug", "randomActionDesc - $randomActionDesc, randomActionName - $randomActionName")
            chooseTag(binding.btnCreateTaskFun)
        }

        binding.onDateClick.setOnClickListener {
            setDate(view)
        }
        binding.onTimeClick.setOnClickListener {
            setTime(view)
        }
        binding.btnCreateTaskDone.setOnClickListener {
            createTaskIsDone()
            var toast: Toast?
            if (createTaskVM.taskCreatingErrorMessage.value.isNullOrEmpty()) {
                toast = Toast.makeText(context, "task added", Toast.LENGTH_SHORT)
                toast.view?.setBackgroundResource(R.color.divider_grey)
                val tv = toast.view?.findViewById<TextView>(android.R.id.message)
                tv?.setTextColor(Color.parseColor("#711547"))
                toast.show()
            } else {
                toast = Toast.makeText(
                    context,
                    createTaskVM.taskCreatingErrorMessage.value,
                    Toast.LENGTH_SHORT
                )
                toast.view?.setBackgroundResource(R.color.divider_grey)
                val tv = toast.view?.findViewById<TextView>(android.R.id.message)
                tv?.setTextColor(Color.parseColor("#711547"))
                toast.show()
            }
        }
        return binding.root
    }

    fun createTaskIsDone() = runBlocking {
        launch {
            createTaskVM.createTask()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCategories(view as ViewGroup)
        setInitialDateTime()
    }

    fun initCategories(root: ViewGroup) {
        tagWork = root.findViewById(R.id.btn_create_task_work)
        tagHome = root.findViewById(R.id.btn_create_task_home)
        tagFamily = root.findViewById(R.id.btn_create_task_family)
        tagMeeting = root.findViewById(R.id.btn_create_task_meeting)
        tagHealth = root.findViewById(R.id.btn_create_task_health)
        tagFun = root.findViewById(R.id.btn_create_task_fun)

        tagFast = root.findViewById(R.id.btn_create_task_fast)
        tagMedium = root.findViewById(R.id.btn_create_task_medium)
        tagCanWait = root.findViewById(R.id.btn_create_task_wait)


        tagWork?.setOnClickListener {
            chooseTag(tagWork)
        }
        tagHome?.setOnClickListener {
            chooseTag(tagHome)
        }
        tagFamily?.setOnClickListener {
            chooseTag(tagFamily)
        }
        tagMeeting?.setOnClickListener {
            chooseTag(tagMeeting)
        }
        tagHealth?.setOnClickListener {
            chooseTag(tagHealth)
        }
        tagFun?.setOnClickListener {
            chooseTag(tagFun)
        }

        tagFast?.setOnClickListener {
            chooseImportantTag(tagFast)
        }
        tagMedium?.setOnClickListener {
            chooseImportantTag(tagMedium)
        }
        tagCanWait?.setOnClickListener {
            chooseImportantTag(tagCanWait)
        }
    }

    private fun chooseTag(button: Button?) {
        createTaskVM.selectedTag.value = button?.text.toString()
        createTaskVM.isTagSelected.value = false
        tagWork?.setBackgroundResource(R.drawable.category_selector)
        tagFamily?.setBackgroundResource(R.drawable.category_selector)
        tagFun?.setBackgroundResource(R.drawable.category_selector)
        tagHealth?.setBackgroundResource(R.drawable.category_selector)
        tagMeeting?.setBackgroundResource(R.drawable.category_selector)
        tagHome?.setBackgroundResource(R.drawable.category_selector)

        tag?.backgroundTintList = null
        tag = button
        tag?.background?.mutate()?.setTint(
            ContextCompat.getColor(
                requireActivity().applicationContext,
                R.color.selected
            )
        )
        createTaskVM.isTagSelected.value = true
    }

    private fun chooseImportantTag(button: Button?) {
        createTaskVM.selectedImportantTag.value = button?.text.toString()
        createTaskVM.isImportnantTagSelected.value = false
        tagFast?.setBackgroundResource(R.drawable.category_important_selector)
        tagMedium?.setBackgroundResource(R.drawable.category_medium_selector)
        tagCanWait?.setBackgroundResource(R.drawable.category_can_wait_selector)

        tagW?.backgroundTintList = null
        tagW = button
        tagW?.background?.mutate()?.setTint(
            ContextCompat.getColor(
                requireActivity().applicationContext,
                R.color.selected
            )
        )
        createTaskVM.isImportnantTagSelected.value = true
    }

    // отображаем диалоговое окно для выбора даты
    fun setDate(v: View?) {
        context?.let {
            DatePickerDialog(
                it, dateSetListener,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
            ).apply {
                show()
                createTaskVM.selectedDay.value = this.datePicker.dayOfMonth
                createTaskVM.selectedMonth.value = this.datePicker.month
                createTaskVM.selectedYear.value = this.datePicker.year
            }
        }
    }

    // отображаем диалоговое окно для выбора времени
    fun setTime(v: View?) {
        TimePickerDialog(
            context, timeSetListener,
            dateAndTime.get(Calendar.HOUR_OF_DAY),
            dateAndTime.get(Calendar.MINUTE), true
        )
            .show()
    }

    // установка начальных даты и времени
    private fun setInitialDateTime() {
        currentDateTime?.text = DateUtils.formatDateTime(
            context,
            dateAndTime.timeInMillis,
            DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                    or DateUtils.FORMAT_SHOW_TIME
        )
    }
}