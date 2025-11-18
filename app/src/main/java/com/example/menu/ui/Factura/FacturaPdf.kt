package com.example.menu.ui.Factura
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import com.example.menu.R
import java.io.ByteArrayOutputStream

object FacturaPdf {

    fun generarFacturaPDF(context: Context, factura: Factura) {

        val pdfDir = File(context.getExternalFilesDir(null), "FacturasPDF")
        if (!pdfDir.exists()) pdfDir.mkdirs()

        val pdfFile = File(pdfDir, "Factura_${factura.codigo}.pdf")

        val document = Document()
        PdfWriter.getInstance(document, FileOutputStream(pdfFile))
        document.open()

        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.img)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val logo = Image.getInstance(stream.toByteArray())
        logo.scaleToFit(80f, 80f)
        logo.alignment = Image.ALIGN_LEFT

        val titulo = Paragraph("FARMACIA ANGELUZ",
            Font(Font.FontFamily.HELVETICA, 18f, Font.BOLD))
        titulo.alignment = Element.ALIGN_CENTER

        document.add(logo)
        document.add(titulo)
        document.add(Paragraph("Factura No: ${factura.codigo}\nFecha: ${factura.fecha}\nCliente: ${factura.clienteNombre}\n\n"))

        // --- Tabla de detalles ---
        val table = PdfPTable(5)
        table.addCell("Producto")
        table.addCell("Cant.")
        table.addCell("Precio")
        table.addCell("IVA")
        table.addCell("Subtotal")

        for (item in factura.detalles) {
            val subtotal = (item.precio * item.cantidad) - item.descuento
            table.addCell(item.producto)
            table.addCell(item.cantidad.toString())
            table.addCell("C$ %.2f".format(item.precio))
            table.addCell("${item.iva}%")
            table.addCell("C$ %.2f".format(subtotal))
        }

        document.add(table)

        // --- Totales ---
        document.add(Paragraph("\nSubtotal: C$ %.2f".format(factura.subtotal())))
        document.add(Paragraph("IVA Total: C$ %.2f".format(factura.totalIva())))
        document.add(Paragraph("TOTAL A PAGAR: C$ %.2f".format(factura.total())))

        document.add(Paragraph("\nGracias por su compra 💊"))
        document.close()
    }

}