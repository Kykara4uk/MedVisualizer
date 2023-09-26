package com.kykara4a.medvisualizer.domain.mappers

import com.kykara4a.medvisualizer.domain.model.DicomObject
import org.dcm4che3.data.Tag
import org.dcm4che3.io.DicomInputStream
import java.io.InputStream

fun InputStream.getDicomObject(): DicomObject {
    val dis = DicomInputStream(this)
    val attrs = dis.readDataset()
    return DicomObject(
        image = attrs.getBytes(Tag.PixelData),
        imageWidth = attrs.getInt(Tag.Columns, -1),
        imageHeight = attrs.getInt(Tag.Rows, -1)
    )
}