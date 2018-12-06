using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ValuesController : ControllerBase
    {
        // GET api/values
        [HttpGet]
        public ActionResult<IEnumerable<string>> Get()
        {
            return new string[] { "Retornei", "Algum", "Json", "Top" };
        }

        [HttpGet("outro")]
        public ActionResult<IEnumerable<string>> Get(string a = null)
        {
            return new string[] { "Retornei", "Outro", "Json", "Top" };
        }
    }
}
