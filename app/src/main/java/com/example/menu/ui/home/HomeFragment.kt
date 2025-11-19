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
import com.example.menu.Models.Productoseneromarzo
import com.example.menu.Models.VentasProductos
import com.example.menu.Models.VentasResponse
import com.example.menu.Models.VentasSucursalAnio
import com.example.menu.Models.VentasSucursalResponse
import com.example.menu.Models.VentasPorDiaNombreSeptiembre
import com.example.menu.ViewModel.ClienteviewModel
import com.example.menu.databinding.FragmentHomeBinding
import com.example.menu.Models.ProductosClientes
import kotlin.collections.map



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
                var productoseneromarzo by mutableStateOf<List< Productoseneromarzo>>(emptyList())
                var ventasseptiembre by mutableStateOf<List<VentasPorDiaNombreSeptiembre>>(emptyList())
                var productosClientes by mutableStateOf<List<ProductosClientes>>(emptyList())









                val clienteViewModel: ClienteviewModel by viewModels()
                clienteViewModel.run {
                    cargarClientes(requireContext())
                    cargarVentasSucursal(requireContext(), "Angeluz")
                    cargarProductos(requireContext())
                    cargarSucursalAnio(requireContext())
                   cargarproductoseneromarzo(requireContext())
                    cargarventaseptiembre(requireContext())
                    cargarProductosClientes(requireContext(), "Angeluz")




                }
                ventas = clienteViewModel.clientes
                // Observar los cambios en ventasSucursal
                ventasSucursal = clienteViewModel.ventasSucursal
                ventasProductos = clienteViewModel.ventasProductos
                ventasSucursalAnio = clienteViewModel.ventasSucursalAnio
                productoseneromarzo = clienteViewModel.productoseneromarzo
                ventasseptiembre = clienteViewModel.ventasseptiembre
                productosClientes = clienteViewModel.productosClientes













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
                                "Reportes",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            BarChartClientes(ventasSucursal as List<VentasSucursalResponse>)

                            Spacer(Modifier.height(20.dp))

                            ProductosBarHorizontalChart(ventasProductos as List<VentasProductos>)

                            Spacer(Modifier.height(20.dp))

                            PieChartVentasSucursalAnio(ventasSucursalAnio as List<VentasSucursalAnio>)

                            Spacer(Modifier.height(20.dp))

                            DonutChartProductosEneroMarzo(productoseneromarzo as List<Productoseneromarzo>)

                            Spacer(Modifier.height(20.dp))

                            PieChartVentasSeptiembre(ventasseptiembre as List<VentasPorDiaNombreSeptiembre>)

                            Spacer(Modifier.height(20.dp))

                            BarChartProductosClientes(productosClientes as List<ProductosClientes>)










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

            val view = AnyChartView(context)
            val chart = AnyChart.column()

            val seriesData = ventas.map {
                ValueDataEntry(it.Cliente ?: "N/A", it.Cantidad ?: 0)
            }

            val dataSet = Set.instantiate()
            dataSet.data(seriesData)

            val barData = dataSet.mapAs("{ x: 'x', value: 'value' }")

            val column = chart.column(barData)

            // Mejora visual
            column.fill("#4CAF50")        // verde elegante
            column.stroke("#388E3C")
            column.hovered().fill("#66BB6A")
            column.hovered().stroke("#2E7D32")

            column.labels()
                .enabled(true)
                .format("{%Value}")
                .fontColor("#000")
                .fontWeight("bold")

            chart.background().fill("#FFFFFF")
            chart.animation(true)
            chart.title("Cantidad de Compras por Cliente en la sucursal Angeluz")
            chart.yAxis(0).title("Cantidad")
            chart.xAxis(0).title("Cliente")

            view.setChart(chart)
            view
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

            val view = AnyChartView(context)
            val chart = AnyChart.bar()

            val seriesData = ventas.map {
                ValueDataEntry(it.Producto ?: "", it.Cantidad ?: 0)
            }

            chart.data(seriesData)

            chart.animation(true)
            chart.title("Cantidad Vendida por Producto")

            chart.yAxis(0).title("Productos")
            chart.xAxis(0).title("Cantidad")

            val series = chart.bar(seriesData)

            series.labels()
                .enabled(true)
                .fontColor("#000")
                .fontWeight("bold")
                .format("{%Value}")

            // Colores modernos
            series.color("#1E88E5")

            chart.background().fill("#FFFFFF")

            view.setChart(chart)
            view
        }
    )
}


@Composable
fun PieChartVentasSucursalAnio(ventas: List<VentasSucursalAnio>) {

    val data = ventas.groupBy { it.Producto ?: "N/A" }
        .map { (producto, lista) ->
            ValueDataEntry(producto, lista.sumOf { it.Total ?: 0 })
        }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp),
        factory = { context ->

            val view = AnyChartView(context)
            val pie = AnyChart.pie()

            // Cargar datos
            pie.data(data)
            pie.animation(true)

            // ⭐ Fondo claro (sin stroke)
            pie.background().fill("#FAFAFA")

            // ⭐ Paleta moderna y contrastante
            pie.palette(arrayOf(
                "#1976D2", // Azul fuerte
                "#43A047", // Verde
                "#FB8C00", // Naranja
                "#E53935", // Rojo
                "#8E24AA", // Morado
                "#00897B", // Verde esmeralda
                "#6D4C41", // Café
                "#FDD835"  // Amarillo
            ))

            // Título
            pie.title("Total de Ventas por Producto (Todos los Años)")

            // Labels claras
            pie.labels()
                .enabled(true)
                .fontSize(12.0)
                .fontWeight("bold")
                .fontColor("#000000")
                .format("{%x}: C$ {%value}")

            view.setChart(pie)
            view
        }
    )
}



@Composable
fun DonutChartProductosEneroMarzo(ventas: List<Productoseneromarzo>) {

    val data = ventas.groupBy { it.Producto ?: "N/A" }
        .map { (producto, lista) ->
            ValueDataEntry(producto, lista.sumOf { it.Cantidad ?: 0 })
        }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp),
        factory = { context ->

            val view = AnyChartView(context)
            val pie = AnyChart.pie()

            pie.innerRadius("55%")
            pie.data(data)
            pie.animation(true)
            pie.background().fill("#FFFFFF")

            pie.palette(arrayOf("#8E24AA", "#3949AB", "#039BE5", "#00897B", "#FDD835"))

            pie.title("Productos más vendidos (Enero - Marzo)")

            pie.labels()
                .enabled(true)
                .fontWeight("bold")
                .fontColor("#000")
                .format("{%x}: {%value}")

            view.setChart(pie)
            view
        }
    )
}



@Composable
fun PieChartVentasSeptiembre(ventas: List<VentasPorDiaNombreSeptiembre>) {

    val data = ventas.map {
        ValueDataEntry(it.NombreDia ?: "N/A", it.Total ?: 0)
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp),
        factory = { context ->

            val view = AnyChartView(context)
            val pie = AnyChart.pie()

            pie.data(data)
            pie.animation(true)
            pie.background().fill("#FFFFFF")

            pie.palette(arrayOf("#29B6F6", "#66BB6A", "#FF7043", "#AB47BC", "#26A69A"))

            pie.title("Ventas por Día (Septiembre)")

            pie.labels()
                .enabled(true)
                .fontWeight("bold")
                .fontColor("#000")
                .format("{%x}: C$ {%value}")

            view.setChart(pie)
            view
        }
    )
}



@Composable
fun BarChartProductosClientes(lista: List<ProductosClientes>) {

    val data = lista
        .groupBy { it.Producto ?: "N/A" }
        .map { (producto, grupo) ->
            ValueDataEntry(producto, grupo.sumOf { it.Total ?: 0 })
        }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        factory = { context ->
            val anyChartView = AnyChartView(context)
            val chart = AnyChart.column()

            // -----------------------
            //  CONFIGURACIÓN VISUAL
            // -----------------------

            chart.data(data)
            chart.animation(true)

            // Fondo claro
            chart.background().fill("#FAFAFA")

            // Paleta moderna (azules, verdes, naranjas)
            chart.palette(arrayOf(
                "#42A5F5", // azul
                "#66BB6A", // verde
                "#FFA726", // naranja
                "#AB47BC", // morado
                "#EF5350", // rojo
                "#26C6DA"  // cian
            ))

            // Título
            chart.title("Total vendido por Producto (Sucursal Angeluz)")

            // Ejes
            chart.yAxis(0).title("Total C$")
            chart.xAxis(0).title("Producto")

            // Labels arriba de cada barra
            val series = chart.column(data)
            series.labels()
                .enabled(true)
                .fontColor("#000000")
                .fontSize(12.0)
                .fontWeight("bold")
                .format("C$ {%Value}")

            // Espaciado visual
            series.tooltip()
                .title(false)
                .format("C$ {%Value}")
                .background().fill("#FFFFFF")

            // Suavidad en animación
            chart.animation(true)

            anyChartView.setChart(chart)
            anyChartView
        }
    )
}

