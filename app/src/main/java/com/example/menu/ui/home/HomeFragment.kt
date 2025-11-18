package com.example.menu.ui.home
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.data.Set
import com.example.menu.Models.VentasProductos
import com.example.menu.Models.VentasResponse
import com.example.menu.Models.VentasSucursalAnio
import com.example.menu.Models.VentasSucursalResponse
import com.example.menu.ViewModel.ClienteviewModel
import com.example.menu.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    @SuppressLint("UnrememberedMutableState")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView: ComposeView = binding.composeView

        composeView.apply {
            setContent {
                var ventas by mutableStateOf<List<VentasResponse>>(emptyList())
                var ventasSucursal by mutableStateOf<List<VentasSucursalResponse>>(emptyList())
                var ventasProductos by mutableStateOf<List<VentasProductos>>(emptyList())
                var ventasSucursalAnio by mutableStateOf<List<VentasSucursalAnio>>(emptyList())




                val clienteViewModel: ClienteviewModel by viewModels()
                clienteViewModel.run {
                    cargarClientes(requireContext())
                    cargarVentasSucursal(requireContext(), "Angeluz")
                    cargarProductos(requireContext())
                    cargarSucursalAnio(requireContext())

                }
                ventas = clienteViewModel.clientes
                // Observar los cambios en ventasSucursal
                ventasSucursal = clienteViewModel.ventasSucursal
                ventasProductos = clienteViewModel.ventasProductos
                ventasSucursalAnio = clienteViewModel.ventasSucursalAnio









                Log.d("Ventas", "Ventas: $ventas")
                Log.d("VentasSucursalFragment", "VentasSucursal: $ventasSucursal")




                val totalVentas = ventas.sumOf { it.Precio?.times(it.Cantidad ?: 0) ?: 0 }
                val totalProductos = ventas.sumOf { it.Cantidad ?: 0 }
                val totalDevoluciones = ventas.count { it.TipoFactura == "Devolución" }


                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                //.scrollable(rememberScrollState(), orientation = Orientation.Vertical)
                                .padding(16.dp)
                        ) {

                            // -------------------------
                            // Estadísticas generales
                            // -------------------------
                            Text(
                                "Resumen de Ventas",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(Modifier.height(12.dp))

                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                DashboardCard(title = "Total Ventas", value = "C$ $totalVentas")
                                DashboardCard(title = "Productos", value = "$totalProductos")
                                DashboardCard(title = "Devoluciones", value = "$totalDevoluciones")
                            }

                            Spacer(Modifier.height(24.dp))

                            // -------------------------
                            // Gráfico AnyChart
                            // -------------------------
                            Text(
                                "Ventas en la Sucursal Angeluz",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            BarChartClientes(ventasSucursal as List<VentasSucursalResponse>)

                            Spacer(Modifier.height(20.dp))

                            ProductosBarHorizontalChart(ventasProductos as List<VentasProductos>)

                            Spacer(Modifier.height(20.dp))

                            PieChartVentasSucursalAnio(ventasSucursalAnio as List<VentasSucursalAnio>)








                        }
                    }
                }

            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

@Composable
fun DashboardCard(title: String, value: String) {
    Column(
        modifier = Modifier
            .width(110.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFEFEFEF))
            .padding(12.dp)
    ) {
        Text(title, fontSize = 12.sp)
        Text(value, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun AnyChartBarChart(ventas: List<VentasResponse>) {

    val data = ventas.groupBy { it.Producto }
        .map { (producto, lista) ->
            producto to lista.sumOf { it.Cantidad ?: 0 }
        }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        factory = { context ->
            val anyChartView = AnyChartView(context)
            val cartesian = AnyChart.bar()

            val seriesData = data.map { (producto, cantidad) ->
                ValueDataEntry(producto, cantidad)
            }

            val set = Set.instantiate().apply {
                data(seriesData)
            }

            val barData = set.mapAs("{ x: 'x', value: 'value' }")

            cartesian.bar(barData)
            cartesian.title("Cantidad Vendida por Producto")

            anyChartView.setChart(cartesian)
            anyChartView
        }
    )
}

@Composable
fun AnyChartPieChart(ventas: List<VentasResponse>) {

    val data = ventas.groupBy { it.Cliente }
        .map { (cliente, lista) ->
            ValueDataEntry(cliente, lista.size)
        }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(530.dp),
        factory = { context ->
            val anyChartView = AnyChartView(context)
            val pie = AnyChart.pie()

            pie.data(data)
            pie.title("Compras por Cliente")

            // Opcional: mostrar labels y porcentajes
            pie.labels().enabled(true)
            pie.labels().format("{%x}: {%value} compras")

            anyChartView.setChart(pie)
            anyChartView
        }
    )
}

@Composable
fun BarChartClientes(ventas: List<VentasSucursalResponse>) {

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        factory = { context ->

            val anyChartView = AnyChartView(context)
            val cartesian = AnyChart.column()

            // Construimos los datos para AnyChart
            val seriesData = ventas.map {
                ValueDataEntry(it.Cliente ?: "N/A", it.Cantidad ?: 0)
            }

            // --- CORRECCIÓN AQUÍ ---
            // Se especifica la clase completa para crear la instancia del Set de datos.
            val dataSet = Set.instantiate()
            dataSet.data(seriesData)

            val barData = dataSet.mapAs("{ x: 'x', value: 'value' }")

            val column = cartesian.column(barData)

            // Etiquetas arriba de cada columna
            column.labels()
                .enabled(true)
                .format("{%Value}")

            cartesian.title("Cantidad de Compras por Cliente")
            cartesian.yAxis(0).title("Cantidad")
            cartesian.xAxis(0).title("Cliente")

            cartesian.animation(true)

            anyChartView.setChart(cartesian)
            anyChartView
        }
    )


}

@Composable
fun ProductosBarHorizontalChart(ventas: List<VentasProductos>) {

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp),
        factory = { context ->
            val anyChartView = com.anychart.AnyChartView(context)

            val chart = com.anychart.AnyChart.bar() // bar horizontal

            // Data
            val seriesData = ventas.map {
                ValueDataEntry(it.Producto ?: "", it.Cantidad ?: 0)
            }

            chart.data(seriesData)

            chart.title("Cantidad Vendida por Producto")
            chart.yAxis(0).title("Productos")
            chart.xAxis(0).title("Cantidad")

            // Mostrar valores al final de cada barra
            val series = chart.bar(seriesData)
            series.labels().enabled(true)
            series.labels().format("{%Value}")

            chart.animation(true)

            anyChartView.setChart(chart)
            anyChartView
        }
    )
}

@Composable
fun PieChartVentasSucursalAnio(ventas: List<VentasSucursalAnio>) {

    // Agrupar por producto y sumar total
    val data = ventas
        .groupBy { it.Producto ?: "N/A" }
        .map { (producto, lista) ->
            ValueDataEntry(producto, lista.sumOf { it.Total ?: 0 })
        }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp),
        factory = { context ->
            val anyChartView = AnyChartView(context)
            val pie = AnyChart.pie()

            pie.data(data)

            pie.title("Total de Ventas por Producto (Todos los Años)")

            // Opcional: labels
            pie.labels().enabled(true)
            pie.labels().format("{%x}: C${'%'}{%value}")

            // Animación
            pie.animation(true)

            anyChartView.setChart(pie)
            anyChartView
        }
    )
}







