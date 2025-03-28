import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appgk.R
import com.example.appgk.databinding.ItemMaytinhBinding
import com.example.appgk.model.MayTinh

class MayTinhAdapter(
    private val onEdit: (MayTinh) -> Unit,
    private val onDelete: (MayTinh) -> Unit
) : ListAdapter<MayTinh, MayTinhAdapter.MayTinhViewHolder>(MayTinhDiffCallback()) {

    class MayTinhViewHolder(val binding: ItemMaytinhBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MayTinhViewHolder {
        val binding = ItemMaytinhBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MayTinhViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MayTinhViewHolder, position: Int) {
        val mayTinh = getItem(position) // Lấy dữ liệu từ ListAdapter
        holder.binding.apply {
            txtTenMay.text = mayTinh.tenmay
            txtLoaiMay.text = mayTinh.loaimay
            txtThanhTien.text = "Thành tiền: ${mayTinh.soluong * mayTinh.dongia} VND"

            btnMore.setOnClickListener {
                val popup = PopupMenu(it.context, it)
                popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.action_edit -> { onEdit(mayTinh); true }
                        R.id.action_delete -> { onDelete(mayTinh); true }
                        else -> false
                    }
                }
                popup.show()
            }
        }
    }
}

class MayTinhDiffCallback : DiffUtil.ItemCallback<MayTinh>() {
    override fun areItemsTheSame(oldItem: MayTinh, newItem: MayTinh): Boolean {
        return oldItem.mamay == newItem.mamay
    }

    override fun areContentsTheSame(oldItem: MayTinh, newItem: MayTinh): Boolean {
        return oldItem == newItem
    }
}
