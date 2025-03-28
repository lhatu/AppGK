package com.example.appgk.fragment

import MayTinhAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appgk.databinding.DialogAddMaytinhBinding
import com.example.appgk.databinding.FragmentMayTinhBinding
import com.example.appgk.model.MayTinh
import com.example.appgk.viewmodel.MayTinhViewModel

class MayTinhFragment : Fragment() {

    private lateinit var binding: FragmentMayTinhBinding
    private lateinit var viewModel: MayTinhViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMayTinhBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MayTinhViewModel::class.java)

        // Xử lý sự kiện khi người dùng nhấn nút "Thêm"
        binding.btnAdd.setOnClickListener {
            showAddDialog()
        }

        // Quan sát LiveData từ ViewModel
        viewModel.mayTinhList.observe(viewLifecycleOwner) { mayTinhList ->
            (binding.recyclerView.adapter as MayTinhAdapter).submitList(mayTinhList)
        }

        // Khởi tạo RecyclerView
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        var adapter = MayTinhAdapter(
            onEdit = { mayTinh -> showEditDialog(mayTinh) },
            onDelete = { mayTinh -> showDeleteConfirmationDialog(mayTinh) }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    // Hiển thị Dialog nhập dữ liệu
    private fun showAddDialog() {
        val dialogBinding = DialogAddMaytinhBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Thêm Máy Tính")
            .setView(dialogBinding.root)
            .setPositiveButton("Thêm") { _, _ ->
                val tenMay = dialogBinding.edtTenMay.text.toString()
                val loaiMay = dialogBinding.edtLoaiMay.text.toString()
                val soLuong = dialogBinding.edtSoLuong.text.toString().toIntOrNull() ?: 0
                val donGia = dialogBinding.edtDonGia.text.toString().toIntOrNull() ?: 0

                if (tenMay.isNotEmpty() && loaiMay.isNotEmpty()) {
                    val mayTinh = MayTinh(0, tenMay, loaiMay, soLuong, donGia)
                    viewModel.insert(mayTinh)
                } else {
                    Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Hủy", null)
            .create()

        dialog.show()
    }

    // Hiển thị Dialog sửa dữ liệu
    private fun showEditDialog(mayTinh: MayTinh) {
        val dialogBinding = DialogAddMaytinhBinding.inflate(layoutInflater)
        dialogBinding.edtTenMay.setText(mayTinh.tenmay)
        dialogBinding.edtLoaiMay.setText(mayTinh.loaimay)
        dialogBinding.edtSoLuong.setText(mayTinh.soluong.toString())
        dialogBinding.edtDonGia.setText(mayTinh.dongia.toString())

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Cập nhật Máy Tính")
            .setView(dialogBinding.root)
            .setPositiveButton("Lưu") { _, _ ->
                val tenMay = dialogBinding.edtTenMay.text.toString()
                val loaiMay = dialogBinding.edtLoaiMay.text.toString()
                val soLuong = dialogBinding.edtSoLuong.text.toString().toIntOrNull() ?: 0
                val donGia = dialogBinding.edtDonGia.text.toString().toIntOrNull() ?: 0

                if (tenMay.isNotEmpty() && loaiMay.isNotEmpty()) {
                    val updatedMayTinh = mayTinh.copy(tenmay = tenMay, loaimay = loaiMay, soluong = soLuong, dongia = donGia)
                    viewModel.update(updatedMayTinh)
                } else {
                    Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Hủy", null)
            .create()

        dialog.show()
    }

    private fun showDeleteConfirmationDialog(mayTinh: MayTinh) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Xác nhận xóa")
            setMessage("Bạn có chắc muốn xóa máy tính '${mayTinh.tenmay}' không?")
            setPositiveButton("Xóa") { _, _ ->
                viewModel.delete(mayTinh) // Xóa khỏi Room Database
            }
            setNegativeButton("Hủy", null)
        }.show()
    }

}