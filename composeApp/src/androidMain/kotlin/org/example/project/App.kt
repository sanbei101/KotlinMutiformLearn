package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class WeatherData(
    val city: String = "上海市",
    val temperature: Int = 26,
    val condition: String = "晴",
    val highTemp: Int = 28,
    val lowTemp: Int = 22,
    val windSpeed: Double = 5.4,
    val humidity: Int = 65,
    val hourlyForecast: List<HourlyWeather> = listOf(
        HourlyWeather("12:00", 27, "☀️"),
        HourlyWeather("15:00", 28, "☀️"),
        HourlyWeather("18:00", 25, "⛅"),
        HourlyWeather("21:00", 24, "🌙"),
        HourlyWeather("23:00", 13, "🌙")
    )
)
data class HourlyWeather(
    val time: String,
    val temp: Int,
    val icon: String
)
val gradient = Brush.verticalGradient(
    colors = listOf(Color(0xFF6B8DD6), Color(0xFF8AA6E2)),
    startY = 0f,
    endY = 500f
)
@Preview
@Composable
fun WeatherCardPreview() {
    MaterialTheme {
        WeatherCard()
    }
}

@Composable
private fun HourlyForecastItem(hourly: HourlyWeather) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient)
        ) {
            Column(
                modifier = Modifier
                    .width(80.dp)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = hourly.time,
                    color = Color.White,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = hourly.icon,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${hourly.temp}°",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun WeatherInfoItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            color = Color.White,
            style = MaterialTheme.typography.body2
        )
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.8f),
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun WeatherCard(weatherData: WeatherData = WeatherData()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(4.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),

                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        weatherData.city,
                        style = MaterialTheme.typography.h4,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${weatherData.temperature}°",
                        style = MaterialTheme.typography.h4,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )
                }


                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp,Alignment.End),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = weatherData.condition,
                        color = Color.White,
                        style = MaterialTheme.typography.h3
                    )
                    Text(
                        text = "☀️",
                        style = MaterialTheme.typography.h4
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))

                // 温度范围
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherInfoItem("最高", "${weatherData.highTemp}°")
                    WeatherInfoItem("最低", "${weatherData.lowTemp}°")
                    WeatherInfoItem("风速", "${weatherData.windSpeed}m/s")
                    WeatherInfoItem("湿度", "${weatherData.humidity}%")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 小时预报
                Text(
                    text = "小时预报",
                    style = MaterialTheme.typography.h6,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyRow (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp,Alignment.Start)
                ){
                    items(weatherData.hourlyForecast) { hourly ->
                        HourlyForecastItem(hourly)
                    }
                }
            }
        }
    }
}
